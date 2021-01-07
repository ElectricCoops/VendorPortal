USE [VendorPortal]
GO
/****** Object:  Schema [sec]    Script Date: 1/7/2021 10:53:45 AM ******/
CREATE SCHEMA [sec]
GO
/****** Object:  Table [sec].[Permission]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [sec].[Permission](
	[PermissionID] [int] IDENTITY(1,1) NOT NULL,
	[Domain] [varchar](50) NOT NULL,
	[Permission] [varchar](128) NOT NULL,
	[EffectiveStartDate] [datetime] NOT NULL,
	[EffectiveEndDate] [datetime] NULL,
 CONSTRAINT [PK_Permission] PRIMARY KEY CLUSTERED 
(
	[PermissionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [sec].[RolePermission]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [sec].[RolePermission](
	[RolePermissionID] [int] IDENTITY(1,1) NOT NULL,
	[RoleID] [int] NOT NULL,
	[PermissionID] [int] NOT NULL,
	[EffectiveStartDate] [datetime] NOT NULL,
	[EffectiveEndDate] [datetime] NULL,
 CONSTRAINT [PK_RolePermission] PRIMARY KEY CLUSTERED 
(
	[RolePermissionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [sec].[UserRole]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [sec].[UserRole](
	[UserRoleID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NOT NULL,
	[RoleID] [int] NOT NULL,
 CONSTRAINT [PK_UserRole] PRIMARY KEY CLUSTERED 
(
	[UserRoleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [sec].[UserTbl]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [sec].[UserTbl](
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[UserName] [varchar](50) NOT NULL,
	[IsLocked] [bit] NULL,
	[FirstName] [varchar](50) NULL,
	[LastName] [varchar](50) NULL,
	[Email] [varchar](50) NULL,
	[Phone] [varchar](50) NULL,
	[PhoneCarrier] [varchar](50) NULL,
	[Password] [varchar](256) NULL,
	[PasswordSalt] [varchar](50) NULL,
	[LastLogin] [datetime] NULL,
	[WorkGroup] [varchar](10) NULL,
	[EffectiveStartDate] [datetime] NULL,
	[EffectiveEndDate] [datetime] NULL,
	[CreatedBy] [varchar](50) NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [sec].[PermissionVW]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [sec].[PermissionVW]
AS
SELECT        p.Domain + ':' + p.Permission AS Permission, u.UserName, ur.RoleID
FROM            sec.UserTbl AS u LEFT OUTER JOIN
                         sec.UserRole AS ur ON ur.UserID = u.UserID LEFT OUTER JOIN
                         sec.RolePermission AS rp ON rp.RoleID = ur.RoleID RIGHT OUTER JOIN
                         sec.Permission AS p ON p.PermissionID = rp.PermissionID

GO
/****** Object:  Table [sec].[Role]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [sec].[Role](
	[RoleID] [int] IDENTITY(1,1) NOT NULL,
	[RoleName] [varchar](128) NOT NULL,
	[EffectiveStartDate] [datetime] NOT NULL,
	[EffectiveEndDate] [datetime] NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[RoleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [sec].[Role2PermissionVW]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [sec].[Role2PermissionVW]
AS

SELECT RoleName, r.RoleID, p.PermissionID, Domain, Permission
  FROM [sec].[Role] r
  LEFT OUTER JOIN sec.RolePermission rp ON rp.RoleID = r.RoleID
  LEFT OUTER JOIN sec.Permission p ON p.PermissionID = rp.PermissionID
GO
/****** Object:  Table [dbo].[InvoiceStatus]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InvoiceStatus](
	[InvoiceStatusId] [int] IDENTITY(1,1) NOT NULL,
	[Active] [bit] NOT NULL,
	[Description] [varchar](50) NOT NULL,
	[TransactionStatus] [varchar](50) NULL,
 CONSTRAINT [PK_InvoiceStatus] PRIMARY KEY CLUSTERED 
(
	[InvoiceStatusId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[StakingSheetDetail]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[StakingSheetDetail](
	[StakingSheetDetailId] [varchar](50) NOT NULL,
	[StakingSheetId] [int] NOT NULL,
	[StationDescription] [varchar](50) NULL,
	[StakingSource] [varchar](50) NULL,
	[AssemblyGuid] [varchar](50) NOT NULL,
	[AssemblyRateGroupId] [int] NULL,
	[AssemblyDescription] [varchar](40) NULL,
	[AssemblyQuantity] [int] NOT NULL,
	[AssemblyActionCode] [varchar](3) NOT NULL,
	[AssemblyCreatedDt] [datetime] NULL,
	[AssemblyModifiedDt] [datetime] NULL,
	[StStatusRefGuid] [varchar](50) NULL,
	[StatusDescription] [varchar](50) NULL,
	[AsBuiltQuantity] [int] NULL,
	[AsBuiltStatusId] [int] NULL,
	[AsBuiltDt] [datetime] NULL,
	[AsBuiltComments] [varchar](256) NULL,
	[AsBuiltBy] [varchar](50) NULL,
	[LcecNotes] [varchar](256) NULL,
	[GL_AccountId] [int] NULL,
	[CurrentInspectionDetailId] [int] NULL,
	[CurrentInspectionDetailStatusId] [int] NULL,
	[CurrentInspectionDetailDt] [datetime] NULL,
	[CurrentInspectorDetailComments] [varchar](50) NULL,
	[CurrentInspectedDetailBy] [varchar](50) NULL,
	[InvoiceStatusId] [int] NULL,
	[InvoiceId] [int] NULL,
	[InvoiceDetailId] [int] NULL,
	[InvoiceSubmitGuid] [varchar](50) NULL,
	[InvoiceApprovedBy] [varchar](50) NULL,
	[InvoiceApprovedDt] [datetime] NULL,
	[InvoiceApprovedComment] [varchar](256) NULL,
	[AssemblyAmount] [decimal](18, 2) NULL,
	[AsBuiltAmount] [decimal](18, 2) NULL,
 CONSTRAINT [PK_StakingSheetStationAssembleUnit] PRIMARY KEY CLUSTERED 
(
	[StakingSheetDetailId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[AsBuiltSummaryVw]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[AsBuiltSummaryVw]
AS
SELECT        dbo.StakingSheetDetail.AssemblyActionCode, dbo.StakingSheetDetail.AssemblyGuid, dbo.StakingSheetDetail.AssemblyDescription, 
     sum(AssemblyQuantity) AS DesignQuantity, sum(AsBuiltQuantity) AS AsBuiltQuantity, dbo.StakingSheetDetail.StakingSheetId, dbo.InvoiceStatus.Description AS InvoiceStatus
FROM            dbo.StakingSheetDetail LEFT OUTER JOIN
                         dbo.InvoiceStatus ON dbo.StakingSheetDetail.InvoiceStatusId = dbo.InvoiceStatus.InvoiceStatusId
GROUP BY dbo.StakingSheetDetail.AssemblyGuid, dbo.StakingSheetDetail.AssemblyActionCode, dbo.StakingSheetDetail.AssemblyDescription, 
                         dbo.StakingSheetDetail.StakingSheetId, dbo.InvoiceStatus.Description



GO
/****** Object:  Table [dbo].[RateGroupPrice]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RateGroupPrice](
	[RateGroupPriceId] [int] IDENTITY(1,1) NOT NULL,
	[RateGroupId] [int] NOT NULL,
	[AssemblyGuid] [varchar](50) NOT NULL,
	[AssemblyDescription] [varchar](60) NULL,
	[AssemblyAction] [varchar](1) NULL,
	[FixedCost] [varchar](20) NULL,
	[LaborConstCost] [varchar](20) NULL,
	[LaborConstHours] [decimal](18, 4) NULL,
	[LaborRetireCost] [varchar](20) NULL,
	[LaborRetireHours] [decimal](18, 4) NULL,
	[EffectiveStartDt] [datetime] NOT NULL,
	[EffectiveEndDt] [datetime] NULL,
	[GLConstAccountId] [int] NOT NULL,
	[GLRetireAccountId] [int] NULL,
	[GLPercent] [decimal](18, 2) NOT NULL,
	[GLActivity] [numeric](4, 0) NULL,
	[GL_Department] [numeric](4, 0) NULL,
	[GLRetireActivity] [numeric](4, 0) NULL,
	[GLRetireDepartment] [numeric](4, 0) NULL,
	[AssemblySource] [varchar](4) NOT NULL,
 CONSTRAINT [PK_RateGroupPrice] PRIMARY KEY CLUSTERED 
(
	[RateGroupPriceId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[AssemblyAdhocVw]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO




CREATE VIEW [dbo].[AssemblyAdhocVw]
AS

		SELECT RateGroupPriceId,
			   AssemblyGuid,
			   RateGroupId,
			   (
			   
			   CASE

					WHEN (RIGHT(AssemblyGuid,4) = '.E.T') THEN

						 'E'

					WHEN (RIGHT(AssemblyGuid,2) = '.E') THEN
					
						 'E'

					
					ELSE 

						'D'
			   END
			   
			   ) AS Energized,
			   (
			   
			   CASE

					WHEN (RIGHT(AssemblyGuid,4) = '.E.T') THEN

						 'T'

					WHEN (RIGHT(AssemblyGuid,2) = '.T') THEN
					
						 'T'

					
					ELSE 

						'N'
			   END
			   
			   ) AS Transfer,
			   EffectiveStartDt,
			   EffectiveEndDt
		FROM dbo.RateGroupPrice


GO
/****** Object:  Table [dbo].[GLAccount]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GLAccount](
	[GLAccountId] [int] IDENTITY(1,1) NOT NULL,
	[GL_Account] [decimal](10, 6) NULL,
	[GL_Description] [varchar](40) NULL,
	[GL_Department] [numeric](4, 0) NULL,
	[GL_Activity] [numeric](4, 0) NULL,
 CONSTRAINT [PK_GLAccount] PRIMARY KEY CLUSTERED 
(
	[GLAccountId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RateGroup]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RateGroup](
	[RateGroupId] [int] IDENTITY(1,1) NOT NULL,
	[VendorId] [int] NOT NULL,
	[RateGroupName] [varchar](50) NOT NULL,
	[Description] [varchar](40) NULL,
	[EffectiveStartDt] [datetime] NOT NULL,
	[EffectiveEndDt] [datetime] NULL,
	[FixedRateSW] [varchar](1) NULL,
	[StatusCode] [varchar](1) NULL,
 CONSTRAINT [PK_RateGroup] PRIMARY KEY CLUSTERED 
(
	[RateGroupId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ServiceOrder]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ServiceOrder](
	[ServiceOrderId] [varchar](50) NOT NULL,
	[StakingSheetId] [int] NULL,
	[SoTypeCode] [varchar](10) NULL,
	[SoTypeCodeDescription] [varchar](30) NULL,
	[SoCloseDt] [datetime] NULL,
	[EnterTypeCode] [varchar](1) NULL,
	[WorkOrderId] [varchar](15) NULL,
	[SoFullName] [varchar](36) NULL,
	[SoDescription] [varchar](4000) NULL,
	[SoStatCode] [varchar](1) NULL,
	[SoCrewId] [varchar](50) NULL,
	[NeededDt] [datetime] NULL,
	[OpenDt] [datetime] NULL,
	[UserName] [varchar](8) NULL,
	[SoFunction] [varchar](2) NULL,
	[WorkFlowId] [int] NULL,
	[ServiceLocationId] [int] NULL,
	[SoComments] [varchar](4000) NULL,
	[ServiceAddress] [varchar](40) NULL,
	[ServiceCity] [varchar](18) NULL,
	[Servicezip] [varchar](6) NULL,
	[ServiceDesc] [varchar](40) NULL,
	[ServiceMapLocation] [varchar](30) NULL,
	[Quantity] [int] NULL,
	[InvoiceId] [int] NULL,
	[InvoiceDetailId] [int] NULL,
	[InvoiceStatusId] [int] NULL,
	[InvoiceSubmitGuid] [varchar](50) NULL,
	[InspectionId] [int] NULL,
	[InspectionStatusId] [int] NULL,
	[InspectedBy] [varchar](50) NULL,
	[InspectedDt] [datetime] NULL,
	[InspectedComment] [varchar](256) NULL,
	[InvoiceApprovedBy] [varchar](50) NULL,
	[InvoiceApprovedDt] [datetime] NULL,
	[InvoiceApprovedComments] [varchar](256) NULL,
 CONSTRAINT [PK_ServiceOrder] PRIMARY KEY CLUSTERED 
(
	[ServiceOrderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[WorkFlow]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[WorkFlow](
	[WorkFlowId] [int] NOT NULL,
	[NeededDt] [datetime] NULL,
	[WorkEventDt] [datetime] NULL,
	[ServiceOrderId] [varchar](50) NULL,
	[ServiceOrderType] [varchar](10) NOT NULL,
	[ServiceOrderPriority] [varchar](1) NULL,
	[WorkGroup] [varchar](50) NULL,
	[WorkOrderId] [varchar](15) NULL,
	[WorkOrderName] [varchar](50) NULL,
	[WorkEventStatusId] [varchar](50) NOT NULL,
	[AssignedVendorId] [int] NULL,
	[AccountId] [varchar](20) NULL,
	[ServiceLocationId] [int] NULL,
	[OpenDt] [datetime] NULL,
	[ResourceId] [int] NULL,
	[ResourceName] [varchar](36) NULL,
	[OverallAsBuiltStatusId] [int] NULL,
	[OverallInspectionStatusId] [int] NULL,
	[OverallInvoiceStatusId] [int] NULL,
	[OverallPaymentStatusId] [varchar](50) NULL,
	[WONotFound] [varchar](50) NULL,
	[DesignCost] [decimal](10, 2) NULL,
	[DesignUniqueAssembly] [int] NULL,
	[DesignTotalAssembly] [int] NULL,
	[DesignTotalStation] [int] NULL,
	[InvoiceCost] [decimal](10, 2) NULL,
	[InvoiceUniqueAssembly] [int] NULL,
	[InvoiceTotalAssembly] [int] NULL,
	[InvoiceTotalStation] [int] NULL,
	[InvoiceVoucherCost] [decimal](10, 2) NULL,
	[InvoiceVoucherStation] [int] NULL,
	[InvoiceVoucherQty] [int] NULL,
 CONSTRAINT [PK_WorkFlow] PRIMARY KEY CLUSTERED 
(
	[WorkFlowId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[ExportWorkOrderInfoVW]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO




CREATE VIEW [dbo].[ExportWorkOrderInfoVW]
AS

SELECT so.WorkOrderId,
		so.ServiceOrderId,
		so.InvoiceId,
		so.InvoiceDetailId,
		rgp.AssemblyGuid,
		rgp.FixedCost,
		rgp.GLPercent,
		ROUND(CAST(rgp.FixedCost AS decimal(10,2)) * so.Quantity * rgp.GLPercent,2,0) AS ConstAmount,
		ROUND(CAST(rgp.FixedCost AS decimal(10,2)) * so.Quantity * rgp.GLPercent,2,1) AS RetireAmount,
		rgp.GLConstAccountId, 
		glc.GL_Account AS GLConstAccount,
		rgp.GLRetireAccountId, 
		glr.GL_Account AS GLRetireAccount,
		rgp.GL_Department, 
		rgp.GLActivity, 
		rgp.GLRetireDepartment, 
		rgp.GLRetireActivity
	FROM dbo.ServiceOrder so
		  LEFT OUTER JOIN dbo.WorkFlow wf ON wf.ServiceOrderId = so.ServiceOrderId
		  LEFT OUTER JOIN dbo.RateGroup rg ON rg.RateGroupName = wf.WorkGroup
		  LEFT OUTER JOIN dbo.RateGroupPrice rgp ON rgp.AssemblyGuid = so.SoTypeCode AND rgp.RateGroupId = rg.RateGroupId
		  LEFT OUTER JOIN dbo.GLAccount glc ON glc.GLAccountId = rgp.GLConstAccountId
		  LEFT OUTER JOIN dbo.GLAccount glr ON glr.GLAccountId = rgp.GLRetireAccountId
	WHERE so.WorkOrderId is not null
		AND so.WorkOrderId <> ''
		AND AssemblyGuid IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST')



GO
/****** Object:  Table [dbo].[InspectionDetail]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InspectionDetail](
	[InspectionDetailId] [int] IDENTITY(1,1) NOT NULL,
	[InspectionDetailDt] [datetime] NULL,
	[StakingSheetDetailId] [varchar](50) NULL,
	[StationId] [varchar](50) NULL,
	[AssembleUnitId] [varchar](50) NOT NULL,
	[InspectionStatusId] [int] NOT NULL,
	[InspectionId] [int] NOT NULL,
	[Comment] [varchar](512) NULL,
 CONSTRAINT [PK_InspectionDetail] PRIMARY KEY CLUSTERED 
(
	[InspectionDetailId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[InspectionDetailVw]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[InspectionDetailVw]
AS
SELECT [InspectionDetailId]
      ,id.[InspectionDetailDt]
      ,id.[StakingSheetDetailId]
      ,id.[StationId]
      ,id.[AssembleUnitId]
      ,id.[InspectionStatusId]
      ,id.[InspectionId]
      ,id.[Comment]
	  ,ssd.[StakingSheetId]
      ,ssd.[StationDescription]
      ,ssd.[AssemblyGuid]
      ,ssd.[AssemblyRateGroupId]
      ,ssd.[AssemblyDescription]
      ,ssd.[AssemblyQuantity]
	  ,ssd.[AssemblyAmount]
      ,ssd.[AssemblyActionCode]
      ,ssd.[AssemblyCreatedDt]
      ,ssd.[AssemblyModifiedDt]
      ,ssd.[StStatusRefGuid]
      ,ssd.[StatusDescription]
      ,ssd.[AsBuiltQuantity]
	  ,ssd.[AsBuiltAmount]
      ,ssd.[AsBuiltStatusId]
      ,ssd.[AsBuiltDt]
      ,ssd.[AsBuiltComments]
      ,ssd.[AsBuiltBy]
      ,ssd.[LcecNotes]
  FROM [VendorPortal].[dbo].[InspectionDetail] id
  LEFT OUTER JOIN dbo.StakingSheetDetail ssd ON (ssd.StakingSheetDetailId = id.StakingSheetDetailId)



GO
/****** Object:  View [dbo].[InvoiceGLSummaryVw]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO



CREATE VIEW [dbo].[InvoiceGLSummaryVw]
AS
SELECT [StakingSheetDetailId]
      ,[StakingSheetId]
	  ,InvoiceId
      ,[StationDescription]
	  ,[AssemblyActionCode]
      ,ssd.[AssemblyGuid]
      ,[AssemblyRateGroupId]
      ,ssd.[AssemblyDescription]
	  ,AssemblyQuantity
      ,[AsBuiltQuantity]
	  ,rgp.LaborConstCost * rgp.LaborConstHours AS ConstCost
	  ,glc.GL_Account AS ConstGLAccount
	  ,rgp.LaborRetireCost * rgp.LaborRetireHours AS RetireCost
	  ,glr.GL_Account AS RetireGlAccount
	  ,ssd.InvoiceStatusId
	  ,ivs.Description AS InvoiceStatus
	  ,InvoiceApprovedComment
  FROM [dbo].[StakingSheetDetail] ssd
  LEFT OUTER JOIN dbo.RateGroupPrice rgp ON rgp.AssemblyGuid = ssd.AssemblyGuid AND rgp.RateGroupId = ssd.AssemblyRateGroupId
  LEFT OUTER JOIN dbo.GLAccount glc ON glc.GLAccountId = rgp.GLConstAccountId
  LEFT OUTER JOIN dbo.GLAccount glr ON glr.GLAccountId = rgp.GLRetireAccountId
  LEFT OUTER JOIN dbo.InvoiceStatus ivs ON ivs.InvoiceStatusId = ssd.InvoiceStatusId



GO
/****** Object:  Table [dbo].[AsBuiltStatus]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AsBuiltStatus](
	[AsBuiltStatusId] [int] IDENTITY(1,1) NOT NULL,
	[Active] [bit] NOT NULL,
	[Description] [varchar](60) NOT NULL,
 CONSTRAINT [PK_AsBuiltStatus] PRIMARY KEY CLUSTERED 
(
	[AsBuiltStatusId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[InspectionStatus]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InspectionStatus](
	[InspectionStatusId] [int] IDENTITY(1,1) NOT NULL,
	[Status] [varchar](50) NULL,
	[Description] [varchar](256) NULL,
	[HeaderDescription] [varchar](256) NULL,
	[HeaderFlg] [bit] NULL,
	[WorkEventStatusId] [varchar](50) NULL,
	[Active] [bit] NULL,
 CONSTRAINT [PK_InspectionStatus] PRIMARY KEY CLUSTERED 
(
	[InspectionStatusId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[StakingSheet]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[StakingSheet](
	[StakingSheetId] [int] IDENTITY(1,1) NOT NULL,
	[WorkFlowId] [int] NOT NULL,
	[WorkOrderId] [varchar](15) NULL,
	[WorkOrderGuid] [varchar](38) NULL,
	[ServiceOrderId] [varchar](50) NULL,
	[WorkOrderDescription] [varchar](255) NULL,
	[WorkOrderComments] [varchar](255) NULL,
	[WorkOrderTypeCode] [varchar](50) NULL,
	[StServiceAddress] [varchar](40) NULL,
	[StServiceAddressCity] [varchar](50) NULL,
	[StServiceAddressPhone] [varchar](13) NULL,
	[StServiceZip] [varchar](50) NULL,
	[StServiceName] [varchar](50) NULL,
 CONSTRAINT [PK_StakingSheet] PRIMARY KEY CLUSTERED 
(
	[StakingSheetId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[StakingSheetDetailGui]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[StakingSheetDetailGui] 
AS
SELECT [StakingSheetDetailId]
      ,ssd.[StakingSheetId]
	  ,ss.WorkOrderId
	  ,ss.WorkFlowId
      ,[StationDescription]
      ,[StakingSource]
      ,[AssemblyGuid]
      ,[AssemblyRateGroupId]
      ,[AssemblyDescription]
      ,[AssemblyQuantity]
      ,[AssemblyActionCode]
      ,[AssemblyCreatedDt]
      ,[AssemblyModifiedDt]
      ,[StStatusRefGuid]
      ,[StatusDescription]
      ,[AsBuiltQuantity]
      ,ssd.[AsBuiltStatusId]
	  ,ab.Description AS AsBuiltStatus
      ,[AsBuiltDt]
      ,[AsBuiltComments]
      ,[AsBuiltBy]
      ,[LcecNotes]
      ,[GL_AccountId]
      ,[CurrentInspectionDetailId]
      ,[CurrentInspectionDetailStatusId]
	  ,insp.Status AS CurrentInspectionDetailStatus
      ,[CurrentInspectionDetailDt]
      ,[CurrentInspectorDetailComments]
      ,[CurrentInspectedDetailBy]
      ,ssd.[InvoiceStatusId]
	  ,inv.Description AS InvoiceStatus
      ,[InvoiceId]
      ,[InvoiceDetailId]
      ,[InvoiceSubmitGuid]
      ,[InvoiceApprovedBy]
      ,[InvoiceApprovedDt]
      ,[InvoiceApprovedComment]
      ,[AssemblyAmount]
      ,[AsBuiltAmount]
  FROM [dbo].[StakingSheetDetail] ssd
  LEFT OUTER JOIN [dbo].[StakingSheet] ss ON ss.StakingSheetId = ssd.StakingSheetId
  LEFT OUTER JOIN [dbo].[AsBuiltStatus] ab ON ab.AsBuiltStatusId = ssd.AsBuiltStatusId
  LEFT OUTER JOIN [dbo].[InspectionStatus] insp ON insp.InspectionStatusId = ssd.CurrentInspectionDetailStatusId
  LEFT OUTER JOIN [dbo].[InvoiceStatus] inv ON inv.InvoiceStatusId = ssd.InvoiceStatusId
GO
/****** Object:  Table [dbo].[Invoice]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Invoice](
	[InvoiceId] [int] IDENTITY(1,1) NOT NULL,
	[InvoiceStatusId] [int] NULL,
	[InvoicedBy] [varchar](50) NOT NULL,
	[InvoicedDt] [datetime] NOT NULL,
	[InvoiceType] [varchar](2) NULL,
	[InvoiceAmount] [decimal](18, 2) NOT NULL,
	[BusinessRuleFlg] [varchar](5) NULL,
	[VendorId] [int] NOT NULL,
	[VendorReference] [varchar](40) NULL,
	[WorkFlowId] [varchar](50) NOT NULL,
	[WorkOrderId] [varchar](50) NULL,
	[ServiceOrderId] [varchar](50) NULL,
	[PaymentDt] [datetime] NULL,
	[PaymentStatus] [varchar](50) NULL,
	[AP_INV_ID] [varchar](50) NULL,
	[ApprovedBy] [varchar](50) NULL,
	[ApprovedDt] [datetime] NULL,
	[ApprovedComment] [varchar](256) NULL,
	[DesignCost] [decimal](10, 2) NULL,
	[DesignUniqueAssembly] [int] NULL,
	[DesignTotalAssembly] [int] NULL,
	[DesignTotalStation] [int] NULL,
	[InvoiceUniqueAssembly] [int] NULL,
	[InvoiceTotalAssembly] [int] NULL,
	[InvoiceTotalStation] [int] NULL,
	[InvoiceVoucherCost] [decimal](10, 2) NULL,
	[InvoiceVoucherQty] [int] NULL,
 CONSTRAINT [PK_Invoice] PRIMARY KEY CLUSTERED 
(
	[InvoiceId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Vendor]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Vendor](
	[VendorId] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](50) NULL,
	[AP_VEND_ID] [varchar](15) NULL,
	[AP_FullName] [varchar](40) NULL,
	[AP_PaymentType] [varchar](1) NULL,
	[GL_Account] [varchar](40) NULL,
 CONSTRAINT [PK_Vendor] PRIMARY KEY CLUSTERED 
(
	[VendorId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[InvoiceSearchVw]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE VIEW [dbo].[InvoiceSearchVw]
AS
SELECT i.InvoiceId, 
	i.InvoiceStatusId, 
	i.InvoicedBy, 
	i.InvoicedDt, 
	i.InvoiceType, 
	i.InvoiceAmount, 
	i.BusinessRuleFlg, 
	i.VendorId, 
	v.Name As VendorName,
	i.VendorReference, 
	i.WorkFlowId, 
	i.WorkOrderId, 
	i.ServiceOrderId, 
	i.PaymentDt, 
	i.PaymentStatus, 
	i.AP_INV_ID, 
	wf.WorkGroup,
	i.ApprovedBy,
	i.ApprovedDt
FROM dbo.Invoice i 
LEFT OUTER JOIN dbo.WorkFlow wf ON i.WorkFlowId = wf.WorkFlowId
LEFT OUTER JOIN dbo.Vendor v ON v.VendorId = i.VendorId




GO
/****** Object:  Table [dbo].[Voucher]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Voucher](
	[VoucherId] [int] IDENTITY(1,1) NOT NULL,
	[StakingSheetId] [int] NULL,
	[WorkOrderId] [varchar](15) NULL,
	[ServiceOrderId] [varchar](50) NULL,
	[StationDescription] [varchar](50) NULL,
	[Description] [varchar](1024) NULL,
	[Crew] [varchar](50) NULL,
	[Amount] [decimal](10, 2) NULL,
	[Requestor] [varchar](50) NULL,
	[CreatedDt] [datetime] NULL,
	[GLAccountId] [int] NULL,
	[GLAccountIdSplit] [int] NULL,
	[InspectionId] [int] NULL,
	[InspectionStatusId] [int] NULL,
	[InspectionComment] [varchar](256) NULL,
	[InvoiceStatusId] [int] NULL,
	[InvoiceId] [int] NULL,
	[InvoiceDetailId] [int] NULL,
	[ApprovedBy] [varchar](50) NULL,
	[ApprovedDt] [datetime] NULL,
	[ApprovedComment] [varchar](256) NULL,
	[SubmitGuid] [varchar](50) NULL,
 CONSTRAINT [PK_Voucher] PRIMARY KEY CLUSTERED 
(
	[VoucherId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[VoucherGui]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE VIEW [dbo].[VoucherGui] 
AS
SELECT v.[VoucherId]
      ,v.[StakingSheetId]
      ,v.[WorkOrderId]
      ,v.[ServiceOrderId]
      ,v.[StationDescription]
      ,v.[Description]
      ,v.[Crew]
      ,v.[Amount]
      ,v.[Requestor]
      ,v.[CreatedDt]
      ,v.[GLAccountId]
      ,v.[GLAccountIdSplit]
      ,v.[InspectionId]
      ,v.[InspectionStatusId]
	  ,insp.Status AS InspectionStatus
      ,v.[InspectionComment]
      ,v.[InvoiceStatusId]
	  ,inv.Description AS InvoiceStatus
      ,v.[InvoiceId]
      ,v.[InvoiceDetailId]
      ,v.[ApprovedBy]
      ,v.[ApprovedDt]
      ,v.[ApprovedComment]
      ,v.[SubmitGuid]
  FROM [dbo].[Voucher] v
  LEFT OUTER JOIN [dbo].[StakingSheet] ss ON ss.StakingSheetId = v.StakingSheetId
  LEFT OUTER JOIN [dbo].[InspectionStatus] insp ON insp.InspectionStatusId = v.InspectionStatusId
  LEFT OUTER JOIN [dbo].[InvoiceStatus] inv ON inv.InvoiceStatusId = v.InvoiceStatusId
GO
/****** Object:  Table [dbo].[InvoiceDetail]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InvoiceDetail](
	[InvoiceDetailId] [int] IDENTITY(1,1) NOT NULL,
	[InvoiceId] [int] NULL,
	[AP_INV_DTL_ID] [varchar](50) NULL,
	[CategoryCode] [varchar](50) NULL,
	[Description] [varchar](50) NULL,
	[Quantity] [int] NULL,
	[Amount] [decimal](18, 2) NULL,
	[GL_Code] [varchar](50) NULL,
	[GLDepartment] [numeric](4, 0) NULL,
	[GLActivity] [numeric](4, 0) NULL,
 CONSTRAINT [PK_InvoiceDetail] PRIMARY KEY CLUSTERED 
(
	[InvoiceDetailId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[InvoiceSyncVw]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE VIEW [dbo].[InvoiceSyncVw]
AS
SELECT i.[InvoiceId]
      ,[InvoiceStatusId]
      ,d.InvoiceDetailId
	  ,d.AP_INV_DTL_ID
	  ,i.InvoiceType
  FROM dbo.[Invoice] i 
  LEFT OUTER JOIN dbo.InvoiceDetail d ON d.InvoiceId = i.InvoiceId
  --WHERE i.InvoiceStatusId IN (6,7,8,9,10,11)


GO
/****** Object:  View [dbo].[RateGroupPriceDistinctVw]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE VIEW [dbo].[RateGroupPriceDistinctVw] AS

SELECT  [RateGroupPriceId]
      ,[RateGroupId]
      ,[AssemblyGuid]
      ,[AssemblyDescription]
      ,[AssemblyAction]
      ,[FixedCost]
      ,[LaborConstCost]
      ,[LaborConstHours]
      ,[LaborRetireCost]
      ,[LaborRetireHours]
      ,[EffectiveStartDt]
      ,[EffectiveEndDt]
      ,[GLConstAccountId]
	  ,[GLRetireAccountId]
      ,[GLPercent]
      ,[GLActivity]
      ,[GL_Department]
      ,[AssemblySource]
  FROM [dbo].[RateGroupPrice]
  WHERE RateGroupPriceId IN (
  
  SELECT MIN(RateGroupPriceId) FROM dbo.RateGroupPrice GROUP BY AssemblyGuid

  );


GO
/****** Object:  View [dbo].[RateGroupVw]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[RateGroupVw]
AS
SELECT        dbo.RateGroupPrice.RateGroupPriceId, dbo.RateGroupPrice.AssemblyGuid, dbo.RateGroupPrice.FixedCost, dbo.RateGroupPrice.LaborConstCost, 
                         dbo.RateGroupPrice.LaborConstHours, dbo.RateGroupPrice.LaborRetireCost, dbo.RateGroupPrice.LaborRetireHours, dbo.RateGroupPrice.EffectiveStartDt, 
                         dbo.RateGroupPrice.EffectiveEndDt, dbo.RateGroup.RateGroupName, dbo.RateGroup.Description
FROM            dbo.RateGroup LEFT OUTER JOIN
                         dbo.RateGroupPrice ON dbo.RateGroup.RateGroupId = dbo.RateGroupPrice.RateGroupId


GO
/****** Object:  View [dbo].[StreetLightSearchVw]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO



CREATE VIEW [dbo].[StreetLightSearchVw]
AS

SELECT so.ServiceOrderId, 
	so.WorkOrderId, 
	so.ServiceMapLocation,
	so.SoStatCode, 
	so.SoTypeCode, 
	so.SoCloseDt,
	so.SoCrewId,
	so.Quantity,
	rgp.FixedCost, 
	rgp.GLConstAccountId, 
	rgp.GLRetireAccountId, 
	glc.GL_Description AS GLConstDescription,
	glr.GL_Description AS GLRetireDescription, 
	rgp.GLPercent, 
	rg.RateGroupName AS WorkGroup, 
	i.InvoiceId, 
	i.VendorReference, 
	invs.InvoiceStatusId,
	invs.Description AS InvoiceStatus,
	so.InspectionId,
	so.InspectionStatusId,
	so.InspectedBy,
	so.InspectedDt,
	so.InspectedComment,
	so.InvoiceApprovedBy,
	so.InvoiceApprovedDt,
	so.InvoiceApprovedComments,
	v.Name AS VendorName
FROM [dbo].[ServiceOrder] so
  LEFT OUTER JOIN dbo.WorkFlow wf ON wf.ServiceOrderId = so.ServiceOrderId
  LEFT OUTER JOIN dbo.RateGroup rg ON rg.RateGroupName = wf.WorkGroup
  LEFT OUTER JOIN dbo.RateGroupPrice rgp ON rgp.AssemblyGuid = wf.ServiceOrderType AND rgp.RateGroupId = rg.RateGroupId
  --LEFT OUTER JOIN dbo.ServiceOrder so ON so.ServiceOrderId = wf.ServiceOrderId
  LEFT OUTER JOIN dbo.Invoice AS i ON i.InvoiceId = so.InvoiceId
  LEFT OUTER JOIN dbo.InvoiceStatus AS invs ON invs.InvoiceStatusId = so.InvoiceStatusId
  LEFT OUTER JOIN dbo.GLAccount AS glc ON glc.GLAccountId = rgp.GLConstAccountId
  LEFT OUTER JOIN dbo.GLAccount AS glr ON glr.GLAccountId = rgp.GLRetireAccountId
  LEFT OUTER JOIN dbo.Vendor v ON v.VendorId = wf.AssignedVendorId
  WHERE so.SoTypeCode IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST')
  AND so.WorkOrderId IS NOT NULL AND so.WorkOrderId <> ''

GO
/****** Object:  Table [dbo].[WorkEventStatus]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[WorkEventStatus](
	[WorkEventStatusId] [varchar](50) NOT NULL,
	[Active] [bit] NOT NULL,
	[Description] [varchar](50) NOT NULL,
	[ApplicationCode] [varchar](1) NULL,
 CONSTRAINT [PK_WorkFlowTaskStatus] PRIMARY KEY CLUSTERED 
(
	[WorkEventStatusId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[WorkFlowSearch_VW]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO




CREATE VIEW [dbo].[WorkFlowSearch_VW]
AS

SELECT 
	wf.WorkFlowId,
	wf.NeededDt,
	wf.WorkEventDt,
	wf.ServiceOrderId,
	wf.ServiceOrderType,
	wf.ServiceOrderPriority,
	wf.WorkGroup,
	wf.WorkOrderId,
	wf.WorkOrderName,
	wf.WorkEventStatusId, 
    wfs.Description AS WorkEventStatus, 
    wf.AssignedVendorId, 
	wf.AccountId, 
	wf.ServiceLocationId, 
	wf.OpenDt,
	wf.ResourceId,
	wf.ResourceName,
	ss.StakingSheetId,
	wf.OverallAsBuiltStatusId,
	ab.Description AS OverallAsBuiltStatus,
	wf.OverallInspectionStatusId,
	ips.Status AS OverallInspectionStatus,
	wf.OverallInvoiceStatusId,
	ivs.Description AS OverallInvoiceStatus,
	so.SoTypeCode, 
    so.SoTypeCodeDescription,
	so.EnterTypeCode, 
	so.SoFullName, 
	so.SoStatCode, 
    so.UserName, 
	so.SoFunction, 
	'SO Comment: ' + so.SoComments + ' SO Description: ' + so.SoDescription AS LCECComments
FROM dbo.WorkFlow wf
LEFT OUTER JOIN dbo.WorkEventStatus wfs ON wf.WorkEventStatusId = wfs.WorkEventStatusId 
LEFT OUTER JOIN dbo.StakingSheet ss ON wf.WorkFlowId = ss.WorkFlowId 
LEFT OUTER JOIN dbo.ServiceOrder so ON wf.WorkFlowId = so.WorkFlowId
LEFT OUTER JOIN dbo.AsBuiltStatus ab ON ab.AsBuiltStatusId = wf.OverallAsBuiltStatusId
LEFT OUTER JOIN dbo.InspectionStatus ips ON ips.InspectionStatusId = wf.OverallInspectionStatusId
LEFT OUTER JOIN dbo.InvoiceStatus ivs ON ivs.InvoiceStatusId = wf.OverallInvoiceStatusId
WHERE so.SoTypeCode NOT IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST')
--(SUBSTRING(so.SoTypeCodeDescription, 1, 12) <> 'STREET LIGHT') 
AND (wf.WorkOrderId IS NOT NULL AND wf.WorkOrderId <> '')




GO
/****** Object:  View [dbo].[WorkOrderAggVw]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE VIEW [dbo].[WorkOrderAggVw]
AS
SELECT w.* 
  FROM dbo.WorkFlow 
  CROSS APPLY (
	VALUES
			(WorkFlowId, 'AU Designed', DesignCost , DesignTotalStation, DesignUniqueAssembly, DesignTotalAssembly)
		   ,(WorkFlowId, 'AU Invoiced', InvoiceCost, InvoiceTotalStation, InvoiceUniqueAssembly, InvoiceTotalAssembly)
		   ,(WorkFlowId, 'Voucher Invoiced', InvoiceVoucherCost, InvoiceVoucherStation, 0, InvoiceVoucherQty)
			
	) w (WorkFlowId, Type, Cost, Station, UniqueAssembly, TotalAssembly)




GO
/****** Object:  Table [dbo].[AGInspection]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AGInspection](
	[AGInspectionID] [int] IDENTITY(1,1) NOT NULL,
	[VendorType] [varchar](10) NULL,
	[Total30Days] [int] NULL,
	[NotInspected] [int] NULL,
	[InspectionReady] [int] NULL,
	[InspectionInProgress] [int] NULL,
	[InspectionApproved30Days] [int] NULL,
	[InspectionRejected] [int] NULL,
	[LastSynced] [datetime] NOT NULL,
 CONSTRAINT [PK_AGInspection] PRIMARY KEY CLUSTERED 
(
	[AGInspectionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[AGInspector]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AGInspector](
	[AGInspectorID] [int] IDENTITY(1,1) NOT NULL,
	[InspectorID] [int] NULL,
	[InspectorName] [varchar](50) NULL,
	[Total30Days] [int] NULL,
	[NotInspected] [int] NULL,
	[InspectionReady] [int] NULL,
	[InspectionInProgress] [int] NULL,
	[InspectionApproved30Days] [int] NULL,
	[InspectionRejected] [int] NULL,
	[LastSynced] [datetime] NOT NULL,
 CONSTRAINT [PK_AGInspector] PRIMARY KEY CLUSTERED 
(
	[AGInspectorID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[AGInvoice]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AGInvoice](
	[AGInvoiceID] [int] IDENTITY(1,1) NOT NULL,
	[VendorType] [varchar](10) NULL,
	[Total30Days] [int] NULL,
	[NotInvoiced] [int] NULL,
	[InvoiceSubmitted] [int] NULL,
	[InvoiceApproved] [int] NULL,
	[InvoiceRejected] [int] NULL,
	[LastSynced] [datetime] NOT NULL,
 CONSTRAINT [PK_AGInvoice] PRIMARY KEY CLUSTERED 
(
	[AGInvoiceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[AGStakingSheet]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AGStakingSheet](
	[AGStakingSheetID] [int] IDENTITY(1,1) NOT NULL,
	[VendorType] [varchar](10) NULL,
	[Total30Days] [int] NULL,
	[AsBuiltNotStarted] [int] NULL,
	[AsbuiltInProgress] [int] NULL,
	[AsBuiltCompleted30Days] [int] NULL,
	[AsBuiltRejected] [int] NULL,
	[AsBuiltAppealed] [int] NULL,
	[AsBuiltCorrected] [int] NULL,
	[NotInspected] [int] NULL,
	[InspectionReady] [int] NULL,
	[InspectionInProgress] [int] NULL,
	[InspectionApproved30Days] [int] NULL,
	[InspectionRejected] [int] NULL,
	[NotInvoiced] [int] NULL,
	[InvoiceSubmitted] [int] NULL,
	[InvoiceApproved] [int] NULL,
	[InvoiceRejected] [int] NULL,
	[WOCompleted30Days] [int] NULL,
	[LastSynced] [datetime] NOT NULL,
 CONSTRAINT [PK_AGStakingSheet] PRIMARY KEY CLUSTERED 
(
	[AGStakingSheetID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[AGStreetlight]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AGStreetlight](
	[AGStreetlightID] [int] IDENTITY(1,1) NOT NULL,
	[VendorType] [varchar](10) NULL,
	[Total30Days] [int] NULL,
	[NotInspected] [int] NULL,
	[InspectionReady] [int] NULL,
	[InspectionInProgress] [int] NULL,
	[InspectionApproved30Days] [int] NULL,
	[InspectionRejected] [int] NULL,
	[NotInvoiced] [int] NULL,
	[InvoiceSubmitted] [int] NULL,
	[InvoiceApproved] [int] NULL,
	[InvoiceRejected] [int] NULL,
	[SOCompleted30Days] [int] NULL,
	[LastSynced] [datetime] NOT NULL,
 CONSTRAINT [PK_AGStreetlight] PRIMARY KEY CLUSTERED 
(
	[AGStreetlightID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[AlertNotification]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AlertNotification](
	[AlertNotificationId] [int] IDENTITY(1,1) NOT NULL,
	[Title] [varchar](50) NOT NULL,
	[Body] [varchar](256) NOT NULL,
	[EffectiveStartDt] [datetime] NOT NULL,
	[EffectiveEndDt] [datetime] NOT NULL,
 CONSTRAINT [PK_AlertNotification] PRIMARY KEY CLUSTERED 
(
	[AlertNotificationId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CurrentContractDate]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CurrentContractDate](
	[CurrentContractDateId] [int] IDENTITY(1,1) NOT NULL,
	[EffectiveStartDt] [datetime] NOT NULL,
	[EffectiveEndDt] [datetime] NOT NULL,
 CONSTRAINT [PK_CurrentContractDate] PRIMARY KEY CLUSTERED 
(
	[CurrentContractDateId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Inspection]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Inspection](
	[InspectionId] [int] IDENTITY(1,1) NOT NULL,
	[InspectionDt] [datetime] NOT NULL,
	[InspectedBy] [int] NOT NULL,
	[InspectionStatusId] [int] NOT NULL,
	[InspectionType] [varchar](2) NULL,
	[Comments] [varchar](512) NULL,
	[WorkOrderId] [varchar](15) NULL,
	[WorkFlowId] [int] NULL,
	[ServiceOrderId] [varchar](50) NULL,
 CONSTRAINT [PK_Inspection] PRIMARY KEY CLUSTERED 
(
	[InspectionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[InspectionUnlock]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InspectionUnlock](
	[InspectionUnlockId] [int] IDENTITY(1,1) NOT NULL,
	[InspectionId] [int] NOT NULL,
	[WorkOrderId] [varchar](15) NOT NULL,
	[VoucherId] [int] NULL,
	[StakingSheetDetailId] [varchar](50) NULL,
	[UnlockedBy] [varchar](50) NOT NULL,
	[UnlockedDt] [datetime] NOT NULL,
 CONSTRAINT [PK_InspectionUnlock] PRIMARY KEY CLUSTERED 
(
	[InspectionUnlockId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Resource]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Resource](
	[ResourceId] [int] NOT NULL,
	[ResourceTypeCd] [varchar](1) NULL,
	[ResourceName] [varchar](36) NULL,
	[Crew] [varchar](4) NULL,
	[UserID] [int] NULL,
	[VpUserID] [int] NULL,
	[EmployeeNumber] [int] NULL,
	[Active] [varchar](1) NULL,
	[EffectiveBeginDt] [datetime] NULL,
	[EffectiveEndDt] [datetime] NULL,
	[GisPersonId] [varchar](50) NULL,
	[CisPersonId] [varchar](50) NULL,
	[AbsPersonId] [varchar](50) NULL,
	[DocVaultPersonId] [varchar](50) NULL,
	[SourceSystem] [varchar](10) NULL,
 CONSTRAINT [PK_Person] PRIMARY KEY CLUSTERED 
(
	[ResourceId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TempWMISWorkRequest]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TempWMISWorkRequest](
	[WorkRequest] [varchar](50) NOT NULL,
	[SyncDate] [datetime] NULL,
 CONSTRAINT [PK_TempWMISWorkRequest] PRIMARY KEY CLUSTERED 
(
	[WorkRequest] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[WorkFlowTask]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[WorkFlowTask](
	[WorkFlowTaskId] [int] NOT NULL,
	[WorkFlowId] [int] NOT NULL,
	[WorkFlowTaskSeq] [varchar](50) NULL,
	[WFTaskCode] [varchar](10) NULL,
	[WFTaskDescription] [varchar](30) NULL,
	[WorkEventStatusId] [varchar](50) NULL,
	[WFEventDt] [datetime] NULL,
	[WFCriticalTask] [varchar](1) NULL,
	[WFTasksCtr] [int] NULL,
	[WorkGroup] [varchar](10) NULL,
	[WorkOrderId] [varchar](15) NULL,
 CONSTRAINT [PK_WorkFlowTask] PRIMARY KEY CLUSTERED 
(
	[WorkFlowTaskId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[WorkGroup]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[WorkGroup](
	[WorkGroupId] [int] IDENTITY(1,1) NOT NULL,
	[ResourceId] [int] NULL,
	[WorkGroupName] [varchar](10) NULL,
	[ListenSw] [varchar](2) NULL,
 CONSTRAINT [PK_WorkGroup] PRIMARY KEY CLUSTERED 
(
	[WorkGroupId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [sec].[LoginAttempt]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [sec].[LoginAttempt](
	[LoginAttemptID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NULL,
	[IPAddress] [varchar](50) NULL,
	[BrowserType] [varchar](256) NULL,
	[AttemptDT] [datetime] NULL,
	[Success] [bit] NULL,
	[NameAttempt] [varchar](50) NULL,
 CONSTRAINT [PK_LoginAttempt] PRIMARY KEY CLUSTERED 
(
	[LoginAttemptID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [sec].[UserPrefInspection]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [sec].[UserPrefInspection](
	[UserPrefId] [int] IDENTITY(1,1) NOT NULL,
	[UserId] [int] NOT NULL,
	[ViewName] [varchar](50) NOT NULL,
	[ViewDefault] [bit] NOT NULL,
	[InspectionId] [bit] NOT NULL,
	[InspectionDt] [bit] NOT NULL,
	[InspectedBy] [bit] NOT NULL,
	[InspectionStatusId] [bit] NOT NULL,
	[InspectionType] [bit] NOT NULL,
	[Comments] [bit] NOT NULL,
	[WorkOrderId] [bit] NOT NULL,
	[WorkFlowId] [bit] NOT NULL,
	[ServiceOrderId] [bit] NOT NULL,
 CONSTRAINT [PK_UserPrefInspection] PRIMARY KEY CLUSTERED 
(
	[UserPrefId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [sec].[UserPrefInspectionSearch]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [sec].[UserPrefInspectionSearch](
	[UserPrefId] [int] IDENTITY(1,1) NOT NULL,
	[UserId] [int] NOT NULL,
	[ViewName] [varchar](50) NOT NULL,
	[ViewDefault] [bit] NOT NULL,
	[InspectionId] [bit] NOT NULL,
	[InspectionDt] [bit] NOT NULL,
	[InspectedBy] [bit] NOT NULL,
	[InspectionStatusId] [bit] NOT NULL,
	[InspectionType] [bit] NOT NULL,
	[Comments] [bit] NOT NULL,
	[WorkOrderId] [bit] NOT NULL,
	[WorkFlowId] [bit] NOT NULL,
	[ServiceOrderId] [bit] NOT NULL,
	[Workgroup] [bit] NOT NULL,
 CONSTRAINT [PK_UserPrefInspectionSearch] PRIMARY KEY CLUSTERED 
(
	[UserPrefId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [sec].[UserPrefInvoice]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [sec].[UserPrefInvoice](
	[UserPrefId] [int] IDENTITY(1,1) NOT NULL,
	[UserId] [int] NOT NULL,
	[ViewName] [varchar](50) NOT NULL,
	[ViewDefault] [bit] NOT NULL,
	[InvoiceId] [bit] NOT NULL,
	[InvoiceStatusId] [bit] NOT NULL,
	[InvoicedBy] [bit] NOT NULL,
	[InvoicedDt] [bit] NOT NULL,
	[InvoiceType] [bit] NOT NULL,
	[InvoiceAmount] [bit] NOT NULL,
	[BusinessRuleFlg] [bit] NOT NULL,
	[VendorId] [bit] NOT NULL,
	[VendorReference] [bit] NOT NULL,
	[WorkFlowId] [bit] NOT NULL,
	[WorkOrderId] [bit] NOT NULL,
	[ServiceOrderId] [bit] NOT NULL,
	[PaymentDt] [bit] NOT NULL,
	[PaymentStatus] [bit] NOT NULL,
	[AP_INV_ID] [bit] NOT NULL,
	[ApprovedBy] [bit] NOT NULL,
	[ApprovedDt] [bit] NOT NULL,
	[ApprovedComment] [bit] NOT NULL,
	[DesignCost] [bit] NOT NULL,
	[DesignUniqueAssembly] [bit] NOT NULL,
	[DesignTotalAssembly] [bit] NOT NULL,
	[DesignTotalStation] [bit] NOT NULL,
	[InvoiceUniqueAssembly] [bit] NOT NULL,
	[InvoiceTotalAssembly] [bit] NOT NULL,
	[InvoiceTotalStation] [bit] NOT NULL,
	[InvoiceVoucherCost] [bit] NOT NULL,
	[InvoiceVoucherQty] [bit] NOT NULL,
	[LCECReference] [bit] NOT NULL,
 CONSTRAINT [PK_UserPrefInvoiceDetail] PRIMARY KEY CLUSTERED 
(
	[UserPrefId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [sec].[UserPrefInvoiceGLSummaryVw]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [sec].[UserPrefInvoiceGLSummaryVw](
	[UserPrefId] [int] IDENTITY(1,1) NOT NULL,
	[UserId] [int] NOT NULL,
	[ViewName] [varchar](50) NOT NULL,
	[ViewDefault] [bit] NOT NULL,
	[StakingSheetDetailId] [bit] NOT NULL,
	[StakingSheetId] [bit] NOT NULL,
	[InvoiceId] [bit] NOT NULL,
	[StationDescription] [bit] NOT NULL,
	[AssemblyActionCode] [bit] NOT NULL,
	[AssemblyGuid] [bit] NOT NULL,
	[AssemblyRateGroupId] [bit] NOT NULL,
	[AssemblyDescription] [bit] NOT NULL,
	[AssemblyQuantity] [bit] NOT NULL,
	[AsBuiltQuantity] [bit] NOT NULL,
	[ConstCost] [bit] NOT NULL,
	[ConstGLAccount] [bit] NOT NULL,
	[RetireCost] [bit] NOT NULL,
	[RetireGlAccount] [bit] NOT NULL,
	[InvoiceStatusId] [bit] NOT NULL,
	[InvoiceStatus] [bit] NOT NULL,
	[InvoiceApprovedComment] [bit] NOT NULL,
	[ExtCost] [bit] NOT NULL,
	[Energize] [bit] NOT NULL,
	[Transfer] [bit] NOT NULL,
 CONSTRAINT [PK_UserPrefInvoiceGLSummaryVw] PRIMARY KEY CLUSTERED 
(
	[UserPrefId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [sec].[UserPrefInvoiceSearch]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [sec].[UserPrefInvoiceSearch](
	[UserPrefId] [int] IDENTITY(1,1) NOT NULL,
	[UserId] [int] NOT NULL,
	[ViewName] [varchar](50) NOT NULL,
	[ViewDefault] [bit] NOT NULL,
	[InvoiceId] [bit] NOT NULL,
	[InvoiceStatusId] [bit] NOT NULL,
	[InvoiceBy] [bit] NOT NULL,
	[InvoiceDt] [bit] NOT NULL,
	[InvoiceType] [bit] NOT NULL,
	[InvoiceAmount] [bit] NOT NULL,
	[BusinessRuleFlg] [bit] NOT NULL,
	[VendorId] [bit] NOT NULL,
	[VendorName] [bit] NOT NULL,
	[VendorReference] [bit] NOT NULL,
	[WorkFlowId] [bit] NOT NULL,
	[WorkOrderId] [bit] NOT NULL,
	[ServiceOrderId] [bit] NOT NULL,
	[PaymentDt] [bit] NOT NULL,
	[PaymentStatus] [bit] NOT NULL,
	[AP_INV_ID] [bit] NOT NULL,
	[WorkGroup] [bit] NOT NULL,
	[ApprovedBy] [bit] NOT NULL,
	[ApprovedDt] [bit] NOT NULL,
	[Action] [bit] NOT NULL,
	[LCECReference] [bit] NOT NULL,
 CONSTRAINT [PK_UserPrefInvoiceSearch] PRIMARY KEY CLUSTERED 
(
	[UserPrefId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [sec].[UserPrefStakingSheetDetail]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [sec].[UserPrefStakingSheetDetail](
	[UserPrefId] [int] IDENTITY(1,1) NOT NULL,
	[UserId] [int] NOT NULL,
	[ViewName] [varchar](50) NOT NULL,
	[ViewDefault] [bit] NOT NULL,
	[StakingSheetDetailId] [bit] NOT NULL,
	[StakingSheetId] [bit] NOT NULL,
	[StationDescription] [bit] NOT NULL,
	[StakingSource] [bit] NOT NULL,
	[AssemblyGuid] [bit] NOT NULL,
	[AssemblyRateGroupId] [bit] NOT NULL,
	[AssemblyDescription] [bit] NOT NULL,
	[AssemblyQuantity] [bit] NOT NULL,
	[AssemblyAmount] [bit] NOT NULL,
	[AssemblyActionCode] [bit] NOT NULL,
	[AssemblyCreatedDt] [bit] NOT NULL,
	[AssemblyModifiedDt] [bit] NOT NULL,
	[StStatusRefGuid] [bit] NOT NULL,
	[StatusDescription] [bit] NOT NULL,
	[AsBuiltQuantity] [bit] NOT NULL,
	[AsBuiltAmount] [bit] NOT NULL,
	[AsBuiltStatusId] [bit] NOT NULL,
	[AsBuiltDt] [bit] NOT NULL,
	[AsBuiltComments] [bit] NOT NULL,
	[AsBuiltBy] [bit] NOT NULL,
	[LcecNotes] [bit] NOT NULL,
	[GL_AccountId] [bit] NOT NULL,
	[CurrentInspectionDetailId] [bit] NOT NULL,
	[CurrentInspectionDetailStatusId] [bit] NOT NULL,
	[CurrentInspectionDetailDt] [bit] NOT NULL,
	[CurrentInspectorDetailComments] [bit] NOT NULL,
	[CurrentInspectedDetailBy] [bit] NOT NULL,
	[InvoiceStatusId] [bit] NOT NULL,
	[InvoiceId] [bit] NOT NULL,
	[InvoiceDetailId] [bit] NOT NULL,
	[InvoiceSubmitGuid] [bit] NOT NULL,
	[InvoiceApprovedBy] [bit] NOT NULL,
	[InvoiceApprovedDt] [bit] NOT NULL,
	[InvoiceApprovedComment] [bit] NOT NULL,
	[Energize] [bit] NOT NULL,
	[Transfer] [bit] NOT NULL,
	[InvoicedBy] [bit] NOT NULL,
	[InvoicedDt] [bit] NOT NULL,
 CONSTRAINT [PK_UserPrefStakingSheetDetail] PRIMARY KEY CLUSTERED 
(
	[UserPrefId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [sec].[UserPrefVoucher]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [sec].[UserPrefVoucher](
	[UserPrefId] [int] IDENTITY(1,1) NOT NULL,
	[UserId] [int] NOT NULL,
	[ViewName] [varchar](50) NOT NULL,
	[ViewDefault] [bit] NOT NULL,
	[VoucherId] [bit] NOT NULL,
	[StakingSheetId] [bit] NOT NULL,
	[WorkOrderId] [bit] NOT NULL,
	[ServiceOrderId] [bit] NOT NULL,
	[StationDescription] [bit] NOT NULL,
	[Description] [bit] NOT NULL,
	[Crew] [bit] NOT NULL,
	[Amount] [bit] NOT NULL,
	[Requestor] [bit] NOT NULL,
	[CreatedDt] [bit] NOT NULL,
	[GLAccountId] [bit] NOT NULL,
	[GLAccountIdSplit] [bit] NOT NULL,
	[InspectionId] [bit] NOT NULL,
	[InspectionStatusId] [bit] NOT NULL,
	[InspectionComment] [bit] NOT NULL,
	[InvoiceStatusId] [bit] NOT NULL,
	[InvoiceId] [bit] NOT NULL,
	[InvoiceDetailId] [bit] NOT NULL,
	[ApprovedBy] [bit] NOT NULL,
	[ApprovedDt] [bit] NOT NULL,
	[ApprovedComment] [bit] NOT NULL,
	[SubmitGuid] [bit] NOT NULL,
 CONSTRAINT [PK_UserPrefVoucher] PRIMARY KEY CLUSTERED 
(
	[UserPrefId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [sec].[UserPrefWorkFlowSearch]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [sec].[UserPrefWorkFlowSearch](
	[UserPrefId] [int] IDENTITY(1,1) NOT NULL,
	[UserId] [int] NOT NULL,
	[ViewName] [varchar](50) NOT NULL,
	[ViewDefault] [bit] NOT NULL,
	[WorkFlowId] [bit] NOT NULL,
	[NeededDt] [bit] NOT NULL,
	[WorkEventDt] [bit] NOT NULL,
	[ServiceOrderId] [bit] NOT NULL,
	[ServiceOrderType] [bit] NOT NULL,
	[ServiceOrderPriority] [bit] NOT NULL,
	[WorkGroup] [bit] NOT NULL,
	[WorkOrderId] [bit] NOT NULL,
	[WorkOrderName] [bit] NOT NULL,
	[WorkEventStatusId] [bit] NOT NULL,
	[WorkEventStatus] [bit] NOT NULL,
	[AssignedVendorId] [bit] NOT NULL,
	[AccountId] [bit] NOT NULL,
	[ServiceLocationId] [bit] NOT NULL,
	[OpenDt] [bit] NOT NULL,
	[ResourceId] [bit] NOT NULL,
	[ResourceName] [bit] NOT NULL,
	[StakingSheetId] [bit] NOT NULL,
	[OverallAsBuiltStatusId] [bit] NOT NULL,
	[OverallAsBuiltStatus] [bit] NOT NULL,
	[OverallInspectionStatusId] [bit] NOT NULL,
	[OverallInspectionStatus] [bit] NOT NULL,
	[OverallInvoiceStatusId] [bit] NOT NULL,
	[OverallInvoiceStatus] [bit] NOT NULL,
	[SoTypeCode] [bit] NOT NULL,
	[SoTypeCodeDescription] [bit] NOT NULL,
	[EnterTypeCode] [bit] NOT NULL,
	[SoFullName] [bit] NOT NULL,
	[SoStatCode] [bit] NOT NULL,
	[UserName] [bit] NOT NULL,
	[SoFunction] [bit] NOT NULL,
	[LCECComments] [bit] NOT NULL,
 CONSTRAINT [PK_UserPrefWorkFlowSearch] PRIMARY KEY CLUSTERED 
(
	[UserPrefId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[InspectionStatus] ADD  CONSTRAINT [DF_InspectionStatus_Active]  DEFAULT ((1)) FOR [Active]
GO
ALTER TABLE [dbo].[StakingSheetDetail] ADD  CONSTRAINT [DF_StakingSheetDetail_StakingSource]  DEFAULT ('GIS') FOR [StakingSource]
GO
ALTER TABLE [sec].[UserPrefStakingSheetDetail] ADD  CONSTRAINT [DF_UserPrefStakingSheetDetail_ViewDefault]  DEFAULT ((0)) FOR [ViewDefault]
GO
ALTER TABLE [sec].[UserPrefStakingSheetDetail] ADD  CONSTRAINT [DF_UserPrefStakingSheetDetail_StakingSheetDetailId]  DEFAULT ((0)) FOR [StakingSheetDetailId]
GO
ALTER TABLE [sec].[UserPrefStakingSheetDetail] ADD  CONSTRAINT [DF_UserPrefStakingSheetDetail_StakingSheetId]  DEFAULT ((0)) FOR [StakingSheetId]
GO
ALTER TABLE [sec].[UserPrefStakingSheetDetail] ADD  CONSTRAINT [DF_UserPrefStakingSheetDetail_StationDescription]  DEFAULT ((0)) FOR [StationDescription]
GO
ALTER TABLE [sec].[UserPrefStakingSheetDetail] ADD  CONSTRAINT [DF_UserPrefStakingSheetDetail_StakingSource]  DEFAULT ((0)) FOR [StakingSource]
GO
ALTER TABLE [dbo].[InspectionDetail]  WITH CHECK ADD  CONSTRAINT [FK_InspectionDetail_Inspection] FOREIGN KEY([InspectionId])
REFERENCES [dbo].[Inspection] ([InspectionId])
GO
ALTER TABLE [dbo].[InspectionDetail] CHECK CONSTRAINT [FK_InspectionDetail_Inspection]
GO
ALTER TABLE [dbo].[InspectionDetail]  WITH CHECK ADD  CONSTRAINT [FK_InspectionDetail_InspectionStatus] FOREIGN KEY([InspectionStatusId])
REFERENCES [dbo].[InspectionStatus] ([InspectionStatusId])
GO
ALTER TABLE [dbo].[InspectionDetail] CHECK CONSTRAINT [FK_InspectionDetail_InspectionStatus]
GO
ALTER TABLE [dbo].[InspectionDetail]  WITH CHECK ADD  CONSTRAINT [FK_InspectionDetail_StakingSheetDetail] FOREIGN KEY([StakingSheetDetailId])
REFERENCES [dbo].[StakingSheetDetail] ([StakingSheetDetailId])
GO
ALTER TABLE [dbo].[InspectionDetail] CHECK CONSTRAINT [FK_InspectionDetail_StakingSheetDetail]
GO
ALTER TABLE [dbo].[InspectionStatus]  WITH CHECK ADD  CONSTRAINT [FK_InspectionStatus_WorkEventStatus] FOREIGN KEY([WorkEventStatusId])
REFERENCES [dbo].[WorkEventStatus] ([WorkEventStatusId])
GO
ALTER TABLE [dbo].[InspectionStatus] CHECK CONSTRAINT [FK_InspectionStatus_WorkEventStatus]
GO
ALTER TABLE [dbo].[InspectionUnlock]  WITH CHECK ADD  CONSTRAINT [FK_InspectionUnlock_Inspection] FOREIGN KEY([InspectionId])
REFERENCES [dbo].[Inspection] ([InspectionId])
GO
ALTER TABLE [dbo].[InspectionUnlock] CHECK CONSTRAINT [FK_InspectionUnlock_Inspection]
GO
ALTER TABLE [dbo].[InspectionUnlock]  WITH CHECK ADD  CONSTRAINT [FK_InspectionUnlock_StakingSheetDetail] FOREIGN KEY([StakingSheetDetailId])
REFERENCES [dbo].[StakingSheetDetail] ([StakingSheetDetailId])
GO
ALTER TABLE [dbo].[InspectionUnlock] CHECK CONSTRAINT [FK_InspectionUnlock_StakingSheetDetail]
GO
ALTER TABLE [dbo].[InspectionUnlock]  WITH CHECK ADD  CONSTRAINT [FK_InspectionUnlock_Voucher] FOREIGN KEY([VoucherId])
REFERENCES [dbo].[Voucher] ([VoucherId])
GO
ALTER TABLE [dbo].[InspectionUnlock] CHECK CONSTRAINT [FK_InspectionUnlock_Voucher]
GO
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD  CONSTRAINT [FK_Invoice_InvoiceStatus] FOREIGN KEY([InvoiceStatusId])
REFERENCES [dbo].[InvoiceStatus] ([InvoiceStatusId])
GO
ALTER TABLE [dbo].[Invoice] CHECK CONSTRAINT [FK_Invoice_InvoiceStatus]
GO
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD  CONSTRAINT [FK_Invoice_Vendor] FOREIGN KEY([VendorId])
REFERENCES [dbo].[Vendor] ([VendorId])
GO
ALTER TABLE [dbo].[Invoice] CHECK CONSTRAINT [FK_Invoice_Vendor]
GO
ALTER TABLE [dbo].[InvoiceDetail]  WITH CHECK ADD  CONSTRAINT [FK_InvoiceDetail_Invoice] FOREIGN KEY([InvoiceId])
REFERENCES [dbo].[Invoice] ([InvoiceId])
GO
ALTER TABLE [dbo].[InvoiceDetail] CHECK CONSTRAINT [FK_InvoiceDetail_Invoice]
GO
ALTER TABLE [dbo].[RateGroup]  WITH CHECK ADD  CONSTRAINT [FK_RateGroup_Vendor1] FOREIGN KEY([VendorId])
REFERENCES [dbo].[Vendor] ([VendorId])
GO
ALTER TABLE [dbo].[RateGroup] CHECK CONSTRAINT [FK_RateGroup_Vendor1]
GO
ALTER TABLE [dbo].[RateGroupPrice]  WITH CHECK ADD  CONSTRAINT [FK_RateGroupPrice_GLAccount] FOREIGN KEY([GLConstAccountId])
REFERENCES [dbo].[GLAccount] ([GLAccountId])
GO
ALTER TABLE [dbo].[RateGroupPrice] CHECK CONSTRAINT [FK_RateGroupPrice_GLAccount]
GO
ALTER TABLE [dbo].[RateGroupPrice]  WITH CHECK ADD  CONSTRAINT [FK_RateGroupPrice_RateGroup] FOREIGN KEY([RateGroupId])
REFERENCES [dbo].[RateGroup] ([RateGroupId])
GO
ALTER TABLE [dbo].[RateGroupPrice] CHECK CONSTRAINT [FK_RateGroupPrice_RateGroup]
GO
ALTER TABLE [dbo].[ServiceOrder]  WITH CHECK ADD  CONSTRAINT [FK_ServiceOrder_WorkFlow] FOREIGN KEY([WorkFlowId])
REFERENCES [dbo].[WorkFlow] ([WorkFlowId])
GO
ALTER TABLE [dbo].[ServiceOrder] CHECK CONSTRAINT [FK_ServiceOrder_WorkFlow]
GO
ALTER TABLE [dbo].[StakingSheet]  WITH CHECK ADD  CONSTRAINT [FK_StakingSheet_WorkFlow] FOREIGN KEY([WorkFlowId])
REFERENCES [dbo].[WorkFlow] ([WorkFlowId])
GO
ALTER TABLE [dbo].[StakingSheet] CHECK CONSTRAINT [FK_StakingSheet_WorkFlow]
GO
ALTER TABLE [dbo].[StakingSheetDetail]  WITH CHECK ADD  CONSTRAINT [FK_StakingSheetDetail_GLAccount] FOREIGN KEY([GL_AccountId])
REFERENCES [dbo].[GLAccount] ([GLAccountId])
GO
ALTER TABLE [dbo].[StakingSheetDetail] CHECK CONSTRAINT [FK_StakingSheetDetail_GLAccount]
GO
ALTER TABLE [dbo].[StakingSheetDetail]  WITH CHECK ADD  CONSTRAINT [FK_StakingSheetDetail_RateGroup] FOREIGN KEY([AssemblyRateGroupId])
REFERENCES [dbo].[RateGroup] ([RateGroupId])
GO
ALTER TABLE [dbo].[StakingSheetDetail] CHECK CONSTRAINT [FK_StakingSheetDetail_RateGroup]
GO
ALTER TABLE [dbo].[StakingSheetDetail]  WITH CHECK ADD  CONSTRAINT [FK_StakingSheetDetail_StakingSheet] FOREIGN KEY([StakingSheetId])
REFERENCES [dbo].[StakingSheet] ([StakingSheetId])
GO
ALTER TABLE [dbo].[StakingSheetDetail] CHECK CONSTRAINT [FK_StakingSheetDetail_StakingSheet]
GO
ALTER TABLE [dbo].[StakingSheetDetail]  WITH CHECK ADD  CONSTRAINT [FK_StakingSheetStationAssembleUnit_AsBuiltStatus] FOREIGN KEY([AsBuiltStatusId])
REFERENCES [dbo].[AsBuiltStatus] ([AsBuiltStatusId])
GO
ALTER TABLE [dbo].[StakingSheetDetail] CHECK CONSTRAINT [FK_StakingSheetStationAssembleUnit_AsBuiltStatus]
GO
ALTER TABLE [dbo].[StakingSheetDetail]  WITH CHECK ADD  CONSTRAINT [FK_StakingSheetStationAssembleUnit_InspectionStatus] FOREIGN KEY([CurrentInspectionDetailStatusId])
REFERENCES [dbo].[InspectionStatus] ([InspectionStatusId])
GO
ALTER TABLE [dbo].[StakingSheetDetail] CHECK CONSTRAINT [FK_StakingSheetStationAssembleUnit_InspectionStatus]
GO
ALTER TABLE [dbo].[StakingSheetDetail]  WITH CHECK ADD  CONSTRAINT [FK_StakingSheetStationAssembleUnit_Invoice] FOREIGN KEY([InvoiceId])
REFERENCES [dbo].[Invoice] ([InvoiceId])
GO
ALTER TABLE [dbo].[StakingSheetDetail] CHECK CONSTRAINT [FK_StakingSheetStationAssembleUnit_Invoice]
GO
ALTER TABLE [dbo].[StakingSheetDetail]  WITH CHECK ADD  CONSTRAINT [FK_StakingSheetStationAssembleUnit_InvoiceStatus] FOREIGN KEY([InvoiceStatusId])
REFERENCES [dbo].[InvoiceStatus] ([InvoiceStatusId])
GO
ALTER TABLE [dbo].[StakingSheetDetail] CHECK CONSTRAINT [FK_StakingSheetStationAssembleUnit_InvoiceStatus]
GO
ALTER TABLE [dbo].[Voucher]  WITH CHECK ADD  CONSTRAINT [FK_Voucher_ServiceOrder] FOREIGN KEY([ServiceOrderId])
REFERENCES [dbo].[ServiceOrder] ([ServiceOrderId])
GO
ALTER TABLE [dbo].[Voucher] CHECK CONSTRAINT [FK_Voucher_ServiceOrder]
GO
ALTER TABLE [dbo].[Voucher]  WITH CHECK ADD  CONSTRAINT [FK_Voucher_StakingSheet] FOREIGN KEY([StakingSheetId])
REFERENCES [dbo].[StakingSheet] ([StakingSheetId])
GO
ALTER TABLE [dbo].[Voucher] CHECK CONSTRAINT [FK_Voucher_StakingSheet]
GO
ALTER TABLE [dbo].[WorkFlow]  WITH CHECK ADD  CONSTRAINT [FK_WorkFlow_AsBuiltStatus] FOREIGN KEY([OverallAsBuiltStatusId])
REFERENCES [dbo].[AsBuiltStatus] ([AsBuiltStatusId])
GO
ALTER TABLE [dbo].[WorkFlow] CHECK CONSTRAINT [FK_WorkFlow_AsBuiltStatus]
GO
ALTER TABLE [dbo].[WorkFlow]  WITH CHECK ADD  CONSTRAINT [FK_WorkFlow_InspectionStatus] FOREIGN KEY([OverallInspectionStatusId])
REFERENCES [dbo].[InspectionStatus] ([InspectionStatusId])
GO
ALTER TABLE [dbo].[WorkFlow] CHECK CONSTRAINT [FK_WorkFlow_InspectionStatus]
GO
ALTER TABLE [dbo].[WorkFlow]  WITH CHECK ADD  CONSTRAINT [FK_WorkFlow_InvoiceStatus] FOREIGN KEY([OverallInvoiceStatusId])
REFERENCES [dbo].[InvoiceStatus] ([InvoiceStatusId])
GO
ALTER TABLE [dbo].[WorkFlow] CHECK CONSTRAINT [FK_WorkFlow_InvoiceStatus]
GO
ALTER TABLE [dbo].[WorkFlow]  WITH CHECK ADD  CONSTRAINT [FK_WorkFlow_Resource] FOREIGN KEY([ResourceId])
REFERENCES [dbo].[Resource] ([ResourceId])
GO
ALTER TABLE [dbo].[WorkFlow] CHECK CONSTRAINT [FK_WorkFlow_Resource]
GO
ALTER TABLE [dbo].[WorkFlow]  WITH CHECK ADD  CONSTRAINT [FK_WorkFlow_Vendor] FOREIGN KEY([AssignedVendorId])
REFERENCES [dbo].[Vendor] ([VendorId])
GO
ALTER TABLE [dbo].[WorkFlow] CHECK CONSTRAINT [FK_WorkFlow_Vendor]
GO
ALTER TABLE [dbo].[WorkFlow]  WITH CHECK ADD  CONSTRAINT [FK_WorkFlow_WorkEventStatus] FOREIGN KEY([WorkEventStatusId])
REFERENCES [dbo].[WorkEventStatus] ([WorkEventStatusId])
GO
ALTER TABLE [dbo].[WorkFlow] CHECK CONSTRAINT [FK_WorkFlow_WorkEventStatus]
GO
ALTER TABLE [dbo].[WorkFlowTask]  WITH CHECK ADD  CONSTRAINT [FK_WorkFlowTask_WorkEventStatus] FOREIGN KEY([WorkEventStatusId])
REFERENCES [dbo].[WorkEventStatus] ([WorkEventStatusId])
GO
ALTER TABLE [dbo].[WorkFlowTask] CHECK CONSTRAINT [FK_WorkFlowTask_WorkEventStatus]
GO
ALTER TABLE [dbo].[WorkFlowTask]  WITH CHECK ADD  CONSTRAINT [FK_WorkFlowTask_WorkFlow] FOREIGN KEY([WorkFlowId])
REFERENCES [dbo].[WorkFlow] ([WorkFlowId])
GO
ALTER TABLE [dbo].[WorkFlowTask] CHECK CONSTRAINT [FK_WorkFlowTask_WorkFlow]
GO
ALTER TABLE [sec].[RolePermission]  WITH CHECK ADD  CONSTRAINT [FK_RolePermission_Permission] FOREIGN KEY([PermissionID])
REFERENCES [sec].[Permission] ([PermissionID])
GO
ALTER TABLE [sec].[RolePermission] CHECK CONSTRAINT [FK_RolePermission_Permission]
GO
ALTER TABLE [sec].[UserPrefInspection]  WITH CHECK ADD  CONSTRAINT [FK_UserPrefInspection_UserTbl] FOREIGN KEY([UserId])
REFERENCES [sec].[UserTbl] ([UserID])
GO
ALTER TABLE [sec].[UserPrefInspection] CHECK CONSTRAINT [FK_UserPrefInspection_UserTbl]
GO
ALTER TABLE [sec].[UserPrefInspectionSearch]  WITH CHECK ADD  CONSTRAINT [FK_UserPrefInspectionSearch_UserTbl] FOREIGN KEY([UserId])
REFERENCES [sec].[UserTbl] ([UserID])
GO
ALTER TABLE [sec].[UserPrefInspectionSearch] CHECK CONSTRAINT [FK_UserPrefInspectionSearch_UserTbl]
GO
ALTER TABLE [sec].[UserPrefInvoice]  WITH CHECK ADD  CONSTRAINT [FK_UserPrefInvoiceDetail_UserTbl] FOREIGN KEY([UserId])
REFERENCES [sec].[UserTbl] ([UserID])
GO
ALTER TABLE [sec].[UserPrefInvoice] CHECK CONSTRAINT [FK_UserPrefInvoiceDetail_UserTbl]
GO
ALTER TABLE [sec].[UserPrefInvoiceGLSummaryVw]  WITH CHECK ADD  CONSTRAINT [FK_UserPrefInvoiceGLSummaryVw_UserTbl] FOREIGN KEY([UserId])
REFERENCES [sec].[UserTbl] ([UserID])
GO
ALTER TABLE [sec].[UserPrefInvoiceGLSummaryVw] CHECK CONSTRAINT [FK_UserPrefInvoiceGLSummaryVw_UserTbl]
GO
ALTER TABLE [sec].[UserPrefInvoiceSearch]  WITH CHECK ADD  CONSTRAINT [FK_UserPrefInvoiceSearch_UserTbl] FOREIGN KEY([UserId])
REFERENCES [sec].[UserTbl] ([UserID])
GO
ALTER TABLE [sec].[UserPrefInvoiceSearch] CHECK CONSTRAINT [FK_UserPrefInvoiceSearch_UserTbl]
GO
ALTER TABLE [sec].[UserPrefStakingSheetDetail]  WITH CHECK ADD  CONSTRAINT [FK_UserPrefStakingSheetDetail_UserTbl] FOREIGN KEY([UserId])
REFERENCES [sec].[UserTbl] ([UserID])
GO
ALTER TABLE [sec].[UserPrefStakingSheetDetail] CHECK CONSTRAINT [FK_UserPrefStakingSheetDetail_UserTbl]
GO
ALTER TABLE [sec].[UserPrefVoucher]  WITH CHECK ADD  CONSTRAINT [FK_UserPrefVoucher_UserTbl] FOREIGN KEY([UserId])
REFERENCES [sec].[UserTbl] ([UserID])
GO
ALTER TABLE [sec].[UserPrefVoucher] CHECK CONSTRAINT [FK_UserPrefVoucher_UserTbl]
GO
ALTER TABLE [sec].[UserPrefWorkFlowSearch]  WITH CHECK ADD  CONSTRAINT [FK_UserPrefWorkFlowSearch_UserTbl] FOREIGN KEY([UserId])
REFERENCES [sec].[UserTbl] ([UserID])
GO
ALTER TABLE [sec].[UserPrefWorkFlowSearch] CHECK CONSTRAINT [FK_UserPrefWorkFlowSearch_UserTbl]
GO
ALTER TABLE [sec].[UserRole]  WITH CHECK ADD  CONSTRAINT [FK_UserRole_Role] FOREIGN KEY([RoleID])
REFERENCES [sec].[Role] ([RoleID])
GO
ALTER TABLE [sec].[UserRole] CHECK CONSTRAINT [FK_UserRole_Role]
GO
/****** Object:  StoredProcedure [dbo].[ASSEMBLY_AMOUNT_FIX]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- ==========================================================
-- Author:		Patrick Bowles
-- Create date: October 3, 2019
-- Description:	asdf
-- ==========================================================
CREATE PROCEDURE [dbo].[ASSEMBLY_AMOUNT_FIX] 
	-- Add the parameters for the stored procedure here
	@IN_StakingSheetID varchar(50),
	--@IN_WorkEventDt datetime,
	@OUT_Response int OUTPUT
AS
BEGIN
	
	DECLARE @vAssemblyQuantity int,
			@vAssemblyGuid varchar(50),
			@vAssemblyRateGroupId int,
			@vAssemblyActionCode varchar(3),
			@vAssemblyAmount decimal(18,2),
			@vStakingSheetDetailId varchar(50),
			@vWorkEventDt datetime

	DECLARE StakingCursor CURSOR
		FOR SELECT 
				AssemblyQuantity,
				AssemblyGuid,
				AssemblyRateGroupId,
				AssemblyActionCode,
				StakingSheetDetailId,
				wf.WorkEventDt
			FROM [VendorPortal].[dbo].[StakingSheetDetail] ssd
			LEFT OUTER JOIN dbo.StakingSheet ss ON ss.StakingSheetId = ssd.StakingSheetId
			LEFT OUTER JOIN dbo.WorkFlow wf ON wf.WorkFlowId = ss.WorkFlowId
			WHERE ssd.StakingSheetId = @IN_StakingSheetID;

	OPEN StakingCursor;

	FETCH NEXT FROM StakingCursor INTO
		@vAssemblyQuantity,
		@vAssemblyGuid,
		@vAssemblyRateGroupId,
		@vAssemblyActionCode,
		@vStakingSheetDetailId,
		@vWorkEventDt;

	WHILE @@FETCH_STATUS=0
		BEGIN
			
			SELECT @vAssemblyAmount =
				CASE @vAssemblyActionCode 
					WHEN 'C' THEN (LaborConstCost * LaborConstHours) 
					WHEN 'R' THEN (LaborRetireCost * LaborRetireHours)
				END
			FROM dbo.RateGroupPrice WHERE AssemblyGuid = @vAssemblyGuid AND RateGroupId = @vAssemblyRateGroupId
				AND @vWorkEventDt BETWEEN EffectiveStartDt AND EffectiveEndDt

			UPDATE dbo.StakingSheetDetail
				SET AssemblyAmount = @vAssemblyAmount * @vAssemblyQuantity
			WHERE StakingSheetDetailId = @vStakingSheetDetailId

			FETCH NEXT FROM StakingCursor INTO
				@vAssemblyQuantity,
				@vAssemblyGuid,
				@vAssemblyRateGroupId,
				@vAssemblyActionCode,
				@vStakingSheetDetailId,
				@vWorkEventDt;
		END;

		
		CLOSE StakingCursor;
		DEALLOCATE StakingCursor;

	SET @OUT_Response = 1
	
END
GO
/****** Object:  StoredProcedure [dbo].[DELETE_NULL_INSPECTIONS]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =======================================================================
-- Author:		Patrick Bowles
-- Create date: June 26, 2019
-- Description:	Delete Inspection record that have no details for WO
-- =======================================================================
CREATE PROCEDURE [dbo].[DELETE_NULL_INSPECTIONS] 
	@IN_WorkOrderId int,
	@OUT_Result int OUTPUT
AS
BEGIN

	DELETE FROM [dbo].[Inspection]
	  WHERE NOT EXISTS(
		SELECT 1 FROM [VendorPortal].[dbo].[InspectionDetail] 
			WHERE Inspection.InspectionId = InspectionDetail.InspectionId 
			AND InspectionDetail.InspectionDetailId IS NOT NULL )
	  AND WorkOrderId = @IN_WorkOrderId
	  AND InspectionStatusId IN (1,4)

	SELECT @OUT_Result = @@ROWCOUNT
    
END
GO
/****** Object:  StoredProcedure [dbo].[GET_ADHOC_ASSEMBLYS]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Patrick Bowles
-- Create date: April 26, 2018
-- Description:	Returns the Rate Group for all vendors pivoted
-- Last Modified: January 29, 2019
-- =============================================
CREATE PROCEDURE [dbo].[GET_ADHOC_ASSEMBLYS] 
	-- Add the parameters for the stored procedure here
	@IN_WorkEventDt datetime,
	@IN_RATEGROUP int,
	@IN_ENERGIZE varchar(1),
	@IN_TRANSFER varchar(1)--,
	--@OUT_ASSEMBLIES CURSOR VARYING OUTPUT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	IF @IN_ENERGIZE = 'E' AND @IN_TRANSFER = 'T'
		BEGIN
		--	SET @OUT_ASSEMBLIES = CURSOR
		--	FORWARD_ONLY STATIC FOR

			SELECT AssemblyGuid
			  FROM dbo.RateGroupPrice
			  WHERE RateGroupId = @IN_RATEGROUP
			  AND RIGHT(AssemblyGuid,4) = '.E.T'    --Energized & Transfer
			  AND @IN_WorkEventDt between EffectiveStartDt AND EffectiveEndDt
			  ORDER BY [AssemblyGuid]
		--	OPEN @OUT_ASSEMBLIES
		END

	ELSE IF @IN_ENERGIZE = 'E' AND @IN_TRANSFER = 'N'
		BEGIN

		--	SET @OUT_ASSEMBLIES = CURSOR
		--	FORWARD_ONLY STATIC FOR

			SELECT AssemblyGuid
			  FROM dbo.RateGroupPrice
			  WHERE RateGroupId = @IN_RATEGROUP
			  AND RIGHT(AssemblyGuid,2) = '.E'      --Energized & Not Transfer
			  AND @IN_WorkEventDt between EffectiveStartDt AND EffectiveEndDt
			  ORDER BY [AssemblyGuid]
		--	OPEN @OUT_ASSEMBLIES
		END

	ELSE IF @IN_ENERGIZE = 'D' AND @IN_TRANSFER = 'T'
		BEGIN
		--	SET @OUT_ASSEMBLIES = CURSOR
		--	FORWARD_ONLY STATIC FOR

			SELECT AssemblyGuid
			  FROM dbo.RateGroupPrice
			  WHERE RateGroupId = @IN_RATEGROUP
			  AND RIGHT(AssemblyGuid,2) = '.T'      --1 Deenergized & Transfer
			  AND RIGHT(AssemblyGuid,4) <> '.E.T'   --2 Deenergized & Transfer
			  AND @IN_WorkEventDt between EffectiveStartDt AND EffectiveEndDt
			  ORDER BY [AssemblyGuid]
		--	OPEN @OUT_ASSEMBLIES
		END

	ELSE IF @IN_ENERGIZE = 'D' AND @IN_TRANSFER = 'N'
		BEGIN
		--	SET @OUT_ASSEMBLIES = CURSOR
		--	FORWARD_ONLY STATIC FOR

			SELECT AssemblyGuid
			  FROM dbo.RateGroupPrice
			  WHERE RateGroupId = @IN_RATEGROUP
			  AND RIGHT(AssemblyGuid,2) <> '.T'     --
			  AND RIGHT(AssemblyGuid,4) <> '.E.T'   -- De Energized & Not Transfer
			  AND RIGHT(AssemblyGuid,2) <> '.E'     --
			  AND @IN_WorkEventDt between EffectiveStartDt AND EffectiveEndDt
			  ORDER BY [AssemblyGuid]

		--	OPEN @OUT_ASSEMBLIES
		END
	
END
GO
/****** Object:  StoredProcedure [dbo].[GET_ASSEMBLY_AMOUNT]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- ======================================================
-- Author:		Patrick Bowles
-- Create date: September 14, 2018
-- Description:	Returns the Amount of the Assembly Unit 
-- Modified On: January 28, 2019
-- ======================================================
CREATE PROCEDURE [dbo].[GET_ASSEMBLY_AMOUNT] 
	-- Add the parameters for the stored procedure here
	@IN_RATEGROUP varchar(50),
	@IN_WORKTYPE varchar(1),
	@IN_AssemblyUnit varchar(50),
	@IN_WorkEventDt datetime,
	@OUT_AMOUNT decimal(10,2) OUTPUT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	SELECT 
		@OUT_AMOUNT =
		CASE @IN_WORKTYPE
			WHEN 'C' THEN rgp.LaborConstHours * rgp.LaborConstCost
			WHEN 'R' THEN rgp.LaborRetireHours * rgp.LaborRetireCost
		END
	 FROM dbo.RateGroupPrice rgp
	 LEFT OUTER JOIN dbo.RateGroup rg ON rg.RateGroupId = rgp.RateGroupId
	 WHERE RateGroupName = @IN_RATEGROUP 
	 AND rgp.AssemblyGuid = @IN_AssemblyUnit
	 AND @IN_WorkEventDt BETWEEN rgp.EffectiveStartDt AND rgp.EffectiveEndDt
END
GO
/****** Object:  StoredProcedure [dbo].[GET_RATEGROUP_PIVOT]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Patrick Bowles
-- Create date: April 26, 2018
-- Description:	Returns the Rate Group for all vendors pivoted
-- =============================================
CREATE PROCEDURE [dbo].[GET_RATEGROUP_PIVOT] 
	-- Add the parameters for the stored procedure here
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	-- Generate a unique list of vendor rate group codes
	DECLARE @DynamicColumns AS NVARCHAR(MAX) = ''
	SELECT @DynamicColumns = ISNULL(@DynamicColumns + ',','') + QUOTENAME(RateGroupName) FROM (SELECT DISTINCT rg.RateGroupName FROM dbo.RateGroup rg LEFT OUTER JOIN dbo.RateGroupPrice rgp ON rgp.RateGroupId = rg.RateGroupId WHERE rg.RateGroupName NOT LIKE 'LC%') AS RateGroups
	SELECT @DynamicColumns = SUBSTRING(@DynamicColumns, 2, len(@DynamicColumns))

	-- Create sql to get the the rate table pivoted
	DECLARE @DynamicQuery AS NVARCHAR(MAX) = ''
	SET @DynamicQuery = 
	N'
	SELECT * FROM (
		SELECT rgp.AssemblyGuid AS AssemblyUnit, rg.RateGroupName as RateGroup, FixedCost FROM [VendorPortal].[dbo].[RateGroupPrice] rgp
LEFT OUTER JOIN dbo.RateGroup rg ON rg.RateGroupId = rgp.RateGroupId
		) AS AssemblyRates

	PIVOT
	(
		max(FixedCost)
		FOR [RateGroup] IN (' + @DynamicColumns + ')) AS pvtAssmeblies
	ORDER BY AssemblyUnit'

	EXECUTE sp_executesql @DynamicQuery

END

GO
/****** Object:  StoredProcedure [dbo].[MergeInspectionDetail]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =======================================================
-- Author:		Patrick Bowles
-- Create date: April 26, 2018
-- Description:	Insert and Update for insepection details
-- =======================================================
CREATE PROCEDURE [dbo].[MergeInspectionDetail]
	@InStakingSheetDetailId varchar(50),
	@InAssemblyGuid varchar(50),
	@InInspectionId int,
	@InInspectionDetailId int,
	@InInspectionDetailStatusId int,
	@InInspectionDetailDt datetime,
	@InInspectionDetailBy varchar(50),
	@InInspectionDetailComment varchar(50),
	@OutResult varchar(50) OUTPUT
AS
BEGIN
	
	DECLARE @vStakingSheetId int,
			@vTotalStakingSheetDetailsNull int,
			@vWorkFlowId int

	SET NOCOUNT ON;

	if @InInspectionDetailId = 0 OR @InInspectionDetailId IS NULL
		BEGIN

			INSERT INTO dbo.InspectionDetail
				(InspectionDetailDt, 
				StakingSheetDetailId,
				AssembleUnitId, 
				InspectionStatusId, 
				InspectionId, 
				Comment)
			VALUES
				(@InInspectionDetailDt, 
				@InStakingSheetDetailId, 
				@InAssemblyGuid, 
				@InInspectionDetailStatusId, 
				@InInspectionId, 
				@InInspectionDetailComment)

			IF (@InInspectionDetailStatusId = 5)
				BEGIN
					UPDATE dbo.StakingSheetDetail
					SET AsBuiltStatusId = 4	
					WHERE StakingSheetDetailId = @InStakingSheetDetailId
				END


			SET @OutResult = SCOPE_IDENTITY()

		END
	Else
		BEGIN
			
			UPDATE dbo.InspectionDetail
				SET InspectionDetailDt = @InInspectionDetailDt, 
					StakingSheetDetailId = @InStakingSheetDetailId, 
					AssembleUnitId = @InAssemblyGuid, 
					InspectionStatusId = @InInspectionDetailStatusId, 
					InspectionId = @InInspectionId, 
					Comment = @InInspectionDetailComment
			WHERE InspectionDetailId = @InInspectionDetailId

			SET @OutResult = @InInspectionDetailId

			IF (@InInspectionDetailStatusId = 5)
				BEGIN
					UPDATE dbo.StakingSheetDetail
					SET AsBuiltStatusId = 4	
					WHERE StakingSheetDetailId = @InStakingSheetDetailId
				END

		END


	UPDATE dbo.StakingSheetDetail
		SET CurrentInspectionDetailId = @OutResult,
			CurrentInspectionDetailStatusId = @InInspectionDetailStatusId,
			CurrentInspectionDetailDt = @InInspectionDetailDt,
			CurrentInspectorDetailComments = @InInspectionDetailComment,
			CurrentInspectedDetailBy = @InInspectionDetailBy	
	WHERE StakingSheetDetailId = @InStakingSheetDetailId

	SELECT @vStakingSheetId = StakingSheetId FROM dbo.StakingSheetDetail WHERE StakingSheetDetailId = @InStakingSheetDetailId

	SELECT @vWorkFlowId = WorkFlowId FROM dbo.StakingSheet WHERE StakingSheetId = @vStakingSheetId

	SELECT @vTotalStakingSheetDetailsNull = count(*) FROM dbo.StakingSheetDetail WHERE StakingSheetId = @vStakingSheetId  AND CurrentInspectionDetailStatusId NOT IN (1, 4)

	IF @vTotalStakingSheetDetailsNull > 0
		BEGIN
			UPDATE dbo.WorkFlow
			SET OverallInspectionStatusId = '3'
			WHERE WorkFlowId = @vWorkFlowId
		END
	ELSE
		BEGIN
			
			UPDATE dbo.WorkFlow
			SET OverallInspectionStatusId = '4'
			WHERE WorkFlowId = @vWorkFlowId

		END
END


GO
/****** Object:  StoredProcedure [dbo].[MergeRateGroupPrice]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		PAtrick Bowles
-- Create date: April 28, 2018
-- Description:	Merge Rate Group Prices
-- =============================================
CREATE PROCEDURE [dbo].[MergeRateGroupPrice] 

	@IN_RateGroupPriceId int = NULL,
	@IN_RateGroupName varchar(4),
	@IN_WorkEventDt datetime,
	@IN_AssemblyGuid varchar(50),
	@IN_FixedCost varchar(50) = 0, 
	@IN_ConstHours decimal(18,2) = 0,
	@IN_ConstCost varchar(50) = 0,
	@IN_RetireHours decimal(18,2) = 0,
	@IN_RetireCost varchar(50) = 0,
	@IN_StartDt datetime,
	@IN_EndDt datetime = NULL,
	@OUT_RateGroupId int OUTPUT,
	@OUT_RateGroupPriceId int OUTPUT
AS
BEGIN
	SET NOCOUNT ON;

	SET @OUT_RateGroupId = (SELECT RateGroupId FROM dbo.RateGroup WHERE RateGroupName = @IN_RateGroupName AND (GETDATE() BETWEEN EffectiveStartDt AND EffectiveEndDt OR (GETDATE() >= EffectiveStartDt AND EffectiveEndDt IS NULL)));

	SET @OUT_RateGroupPriceId = (SELECT RateGroupPriceId FROM dbo.RateGroupPrice WHERE RateGroupId = @OUT_RateGroupId AND AssemblyGuid = @IN_AssemblyGuid AND (@IN_WorkEventDt BETWEEN EffectiveStartDt AND EffectiveEndDt OR (@IN_WorkEventDt >= EffectiveStartDt AND EffectiveEndDt IS NULL)));

	IF @OUT_RateGroupPriceId IS NULL
		BEGIN --Insert
			
			INSERT INTO dbo.RateGroupPrice
			(RateGroupId,AssemblyGuid,FixedCost,LaborConstCost,LaborConstHours,LaborRetireCost,LaborRetireHours,EffectiveStartDt,EffectiveEndDt)
			VALUES
			(1,@IN_AssemblyGuid,@IN_FixedCost,@IN_ConstCost,@IN_ConstHours,@IN_RetireCost,@IN_RetireHours,@IN_StartDt,@IN_EndDt)
			
			SET @OUT_RateGroupPriceId = SCOPE_IDENTITY();
		END
	ELSE
		BEGIN --Update

			UPDATE dbo.RateGroupPrice
			SET RateGroupId = @OUT_RateGroupId,
				AssemblyGuid = @IN_AssemblyGuid,
				FixedCost = @IN_FixedCost,
				LaborConstCost = @IN_ConstCost,
				LaborConstHours = @IN_ConstHours,
				LaborRetireCost = @IN_RetireCost,
				LaborRetireHours = @IN_RetireHours,
				EffectiveStartDt = @IN_StartDt,
				EffectiveEndDt = @IN_EndDt
			WHERE RateGroupPriceId = @OUT_RateGroupPriceId

			--SET @OUT_RateGroupPriceId = @IN_RateGroupPriceId;
		END
END
GO
/****** Object:  StoredProcedure [dbo].[NEW_AGGREGATE_SYNC_INSP]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- ================================================================
-- Author:		Patrick Bowles
-- Create date: August 14, 2018
-- Updated: October 22, 2020
-- Description:	Build aggregate data for dashboard on VendorPortal
-- ================================================================
CREATE PROCEDURE [dbo].[NEW_AGGREGATE_SYNC_INSP]
	@vVendorType varchar(10)
AS
BEGIN
	DECLARE @vTotal30Days int,
			@vNotInspected int,
			@vInspectionReady int,
			@vInspectionInProgress int,
			@vInspectionApproved30Days int,
			@vInspectionRejected int,
			@vLastSynced datetime

	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	DELETE FROM dbo.AGInspection WHERE VendorType = @vVendorType

	SELECT @vLastSynced = GETDATE()

	SELECT @vTotal30Days = COUNT(*) FROM [dbo].[Inspection] i
	LEFT OUTER JOIN dbo.WorkFlow wf ON wf.WorkOrderId = i.WorkOrderId
		WHERE i.InspectionDt > DATEADD(Day, -30, getdate())
		AND wf.WorkGroup = @vVendorType

	SELECT @vNotInspected = COUNT(*) FROM [dbo].[Inspection] i
		LEFT OUTER JOIN dbo.WorkFlow wf ON wf.WorkOrderId = i.WorkOrderId
		WHERE i.InspectionDt > DATEADD(Day, -30, getdate()) 
		AND i.InspectionStatusId IN (SELECT InspectionStatusId FROM dbo.InspectionStatus WHERE Status = 'Not Inspected')
		AND wf.WorkGroup = @vVendorType

	SELECT @vInspectionReady = COUNT(*) FROM [dbo].[Inspection] i
	LEFT OUTER JOIN dbo.WorkFlow wf ON wf.WorkOrderId = i.WorkOrderId
		WHERE i.InspectionDt > DATEADD(Day, -30, getdate())
		AND i.InspectionStatusId IN (SELECT InspectionStatusId FROM dbo.InspectionStatus WHERE Status = 'Ready for Inspection')
		AND wf.WorkGroup = @vVendorType

	SELECT @vInspectionInProgress = COUNT(*) FROM [dbo].[Inspection] i
	LEFT OUTER JOIN dbo.WorkFlow wf ON wf.WorkOrderId = i.WorkOrderId
		WHERE i.InspectionDt > DATEADD(Day, -30, getdate())
		AND i.InspectionStatusId IN (SELECT InspectionStatusId FROM dbo.InspectionStatus WHERE Status = 'In Progress')
		AND wf.WorkGroup = @vVendorType

	SELECT @vInspectionApproved30Days = COUNT(*) FROM [dbo].[Inspection] i
	LEFT OUTER JOIN dbo.WorkFlow wf ON wf.WorkOrderId = i.WorkOrderId
		WHERE i.InspectionDt > DATEADD(Day, -30, getdate())
		AND i.InspectionStatusId IN (SELECT InspectionStatusId FROM dbo.InspectionStatus WHERE Status = 'Approved')
		AND wf.WorkGroup = @vVendorType

	SELECT @vInspectionRejected = COUNT(*) FROM [dbo].[Inspection] i
	LEFT OUTER JOIN dbo.WorkFlow wf ON wf.WorkOrderId = i.WorkOrderId
		WHERE i.InspectionDt > DATEADD(Day, -30, getdate())
		AND i.InspectionStatusId IN (SELECT InspectionStatusId FROM dbo.InspectionStatus WHERE Status = 'Rejected')
		AND wf.WorkGroup = @vVendorType

    INSERT INTO dbo.AGInspection
	(Total30Days,
	 VendorType,
	 NotInspected,
	 InspectionReady,
	 InspectionInProgress,
	 InspectionApproved30Days,
	 InspectionRejected,
	 LastSynced) 
	VALUES 
	(@vTotal30Days,
	 @vVendorType,
	 @vNotInspected,
	 @vInspectionReady,
	 @vInspectionInProgress,
	 @vInspectionApproved30Days,
	 @vInspectionRejected,
	 @vLastSynced)
END
GO
/****** Object:  StoredProcedure [dbo].[NEW_AGGREGATE_SYNC_INV]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- ================================================================
-- Author:		Patrick Bowles
-- Create date: August 14, 2018
-- Description:	Build aggregate data for dashboard on VendorPortal
-- ================================================================
CREATE PROCEDURE [dbo].[NEW_AGGREGATE_SYNC_INV]
	@vVendorType varchar(10)
AS
BEGIN
	DECLARE @vTotal30Days int,
			@vNotInvoiced int,
			@vInvoiceSubmitted int,
			@vInvoiceApproved int,
			@vInvoiceRejected int,
			@vLastSynced datetime

	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	DELETE FROM dbo.AGInvoice WHERE VendorType = @vVendorType

	SELECT @vLastSynced = GETDATE()

  SELECT @vTotal30Days = COUNT(*) 
  FROM [dbo].[ServiceOrder] s
  LEFT OUTER JOIN dbo.WorkFlow wf ON wf.ServiceOrderId = s.ServiceOrderId
  LEFT OUTER JOIN dbo.Invoice i ON i.InvoiceId = s.InvoiceId
  WHERE s.InvoiceId is not null
  AND i.InvoicedDt > DATEADD(Day, -30, getdate())
  AND wf.WorkGroup = @vVendorType

  SELECT @vNotInvoiced = isnull(SUM(CASE WHEN wf.OverallInvoiceStatusId = 1 THEN 1 ELSE 0 END),0) 
  FROM [dbo].[ServiceOrder] s
  LEFT OUTER JOIN dbo.WorkFlow wf ON wf.ServiceOrderId = s.ServiceOrderId
  LEFT OUTER JOIN dbo.Invoice i ON i.InvoiceId = s.InvoiceId
  WHERE s.InvoiceId is not null
  AND i.InvoicedDt > DATEADD(Day, -30, getdate())
  AND wf.WorkGroup = @vVendorType

  SELECT 
	@vInvoiceSubmitted = isnull(SUM(CASE WHEN i.InvoiceStatusId = 2 THEN 1 ELSE 0 END),0),
	@vInvoiceApproved = isnull(SUM(CASE WHEN i.InvoiceStatusId = 4 THEN 1 ELSE 0 END),0),
	@vInvoiceRejected = isnull(SUM(CASE WHEN i.InvoiceStatusId = 3 THEN 1 ELSE 0 END),0)
  FROM [dbo].[ServiceOrder] s
  LEFT OUTER JOIN dbo.WorkFlow wf ON wf.ServiceOrderId = s.ServiceOrderId
  LEFT OUTER JOIN dbo.Invoice i ON i.InvoiceId = s.InvoiceId
  WHERE s.InvoiceId is not null
  AND i.InvoicedDt > DATEADD(Day, -30, getdate())
  AND wf.WorkGroup = @vVendorType

    INSERT INTO dbo.AGInvoice
	(VendorType,
	 Total30Days,
	 NotInvoiced,
	 InvoiceSubmitted,
	 InvoiceApproved,
	 InvoiceRejected,
	 LastSynced) 
	VALUES 
	(@vVendorType,
	 @vTotal30Days,
	 @vNotInvoiced,
	 @vInvoiceSubmitted,
	 @vInvoiceApproved,
	 @vInvoiceRejected,
	 @vLastSynced)
END

GO
/****** Object:  StoredProcedure [dbo].[NEW_AGGREGATE_SYNC_SL]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- ================================================================
-- Author:		Patrick Bowles
-- Create date: August 14, 2018
-- Description:	Build aggregate data for dashboard on VendorPortal
-- ================================================================
CREATE PROCEDURE [dbo].[NEW_AGGREGATE_SYNC_SL]
	@vVendorType varchar(10)
AS
BEGIN
	DECLARE @vTotal30Days int,
			@vNotInspected int,
			@vInspectionReady int,
			@vInspectionInProgress int,
			@vInspectionApproved30Days int,
			@vInspectionRejected int,
			@vNotInvoiced int,
			@vInvoiceSubmitted int,
			@vInvoiceApproved int,
			@vInvoiceRejected int,
			@vSOCompleted30Days int,
			@vLastSynced datetime

	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	DELETE FROM dbo.AGStreetlight WHERE VendorType = @vVendorType

	SELECT @vLastSynced = GETDATE()

	SELECT @vTotal30Days = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType AND WorkEventDt > DATEADD(Day, -30, getdate())

	SELECT @vNotInspected = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType --AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallInspectionStatusId IN (SELECT InspectionStatusId FROM dbo.InspectionStatus WHERE Status = 'Not Inspected')

	SELECT @vInspectionReady = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType --AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallInspectionStatusId IN (SELECT InspectionStatusId FROM dbo.InspectionStatus WHERE Status = 'Ready for Inspection')

	SELECT @vInspectionInProgress = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType --AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallInspectionStatusId IN (SELECT InspectionStatusId FROM dbo.InspectionStatus WHERE Status = 'In Progress')

	SELECT @vInspectionApproved30Days = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallInspectionStatusId IN (SELECT InspectionStatusId FROM dbo.InspectionStatus WHERE Status = 'Approved')

	SELECT @vInspectionRejected = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType --AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallInspectionStatusId IN (SELECT InspectionStatusId FROM dbo.InspectionStatus WHERE Status = 'Rejected')

	SELECT @vNotInvoiced = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType --AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallInvoiceStatusId IN (SELECT InvoiceStatusId FROM dbo.InvoiceStatus WHERE Description = 'Not Invoiced')

	SELECT @vInvoiceSubmitted = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType --AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallInvoiceStatusId IN (SELECT InvoiceStatusId FROM dbo.InvoiceStatus WHERE Description = 'Submitted')

	SELECT @vInvoiceApproved = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType --AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallInvoiceStatusId IN (SELECT InvoiceStatusId FROM dbo.InvoiceStatus WHERE Description = 'Approved')

	SELECT @vInvoiceRejected = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType --AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallInvoiceStatusId IN (SELECT InvoiceStatusId FROM dbo.InvoiceStatus WHERE Description = 'Rejected')

	SELECT @vSOCompleted30Days = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallAsBuiltStatusId IN (SELECT AsBuiltStatusId FROM dbo.AsBuiltStatus WHERE Description = 'Completed')
		AND OverallInspectionStatusId IN (SELECT InspectionStatusId FROM dbo.InspectionStatus WHERE Status = 'Approved')
		AND OverallInvoiceStatusId IN (SELECT InvoiceStatusId FROM dbo.InvoiceStatus WHERE Description IN ('Approved','Exported','Imported'))

    INSERT INTO dbo.AGStreetlight
	(Total30Days,
	 VendorType,
	 NotInspected,
	 InspectionReady,
	 InspectionInProgress,
	 InspectionApproved30Days,
	 InspectionRejected,
	 NotInvoiced,
	 InvoiceSubmitted,
	 InvoiceApproved,
	 InvoiceRejected,
	 SOCompleted30Days,
	 LastSynced) 
	VALUES 
	(@vTotal30Days,
	 @vVendorType,
	 @vNotInspected,
	 @vInspectionReady,
	 @vInspectionInProgress,
	 @vInspectionApproved30Days,
	 @vInspectionRejected,
	 @vNotInvoiced,
	 @vInvoiceSubmitted,
	 @vInvoiceApproved,
	 @vInvoiceRejected,
	 @vSOCompleted30Days,
	 @vLastSynced)
END

GO
/****** Object:  StoredProcedure [dbo].[NEW_AGGREGATE_SYNC_SS]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- ================================================================
-- Author:		Patrick Bowles
-- Create date: August 12, 2018
-- Description:	Build aggregate data for dashboard on VendorPortal
-- ================================================================
CREATE PROCEDURE [dbo].[NEW_AGGREGATE_SYNC_SS]
	@vVendorType varchar(10)
AS
BEGIN
	DECLARE @vTotal30Days int,
			@vAsBuiltNotStarted int,
			@vAsBuiltInProgress int,
			@vAsBuiltCompleted30Days int,
			@vAsBuiltRejected int,
			@vAsBuiltAppealed int,
			@vAsBuiltCorrected int,
			@vNotInspected int,
			@vInspectionReady int,
			@vInspectionInProgress int,
			@vInspectionApproved30Days int,
			@vInspectionRejected int,
			@vNotInvoiced int,
			@vInvoiceSubmitted int,
			@vInvoiceApproved int,
			@vInvoiceRejected int,
			@vWOCompleted30Days int,
			@vLastSynced datetime

	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	DELETE FROM dbo.AGStakingSheet WHERE VendorType = @vVendorType

	SELECT @vLastSynced = GETDATE()

	SELECT @vTotal30Days = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType NOT IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType AND WorkEventDt > DATEADD(Day, -30, getdate())

	SELECT @vAsBuiltNotStarted = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType NOT IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType --AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallAsBuiltStatusId IN (SELECT AsBuiltStatusId FROM dbo.AsBuiltStatus WHERE Description = 'Not Started')

	SELECT @vAsBuiltInProgress = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType NOT IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType --AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallAsBuiltStatusId IN (SELECT AsBuiltStatusId FROM dbo.AsBuiltStatus WHERE Description = 'In Progress')

	SELECT @vAsBuiltCompleted30Days = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType NOT IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallAsBuiltStatusId IN (SELECT AsBuiltStatusId FROM dbo.AsBuiltStatus WHERE Description = 'Completed')

	SELECT @vAsBuiltRejected = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType NOT IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType --AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallAsBuiltStatusId IN (SELECT AsBuiltStatusId FROM dbo.AsBuiltStatus WHERE Description = 'Rejected')

	SELECT @vAsBuiltAppealed = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType NOT IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType --AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallAsBuiltStatusId IN (SELECT AsBuiltStatusId FROM dbo.AsBuiltStatus WHERE Description = 'Appealed')

	SELECT @vAsBuiltCorrected = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType NOT IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType --AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallAsBuiltStatusId IN (SELECT AsBuiltStatusId FROM dbo.AsBuiltStatus WHERE Description = 'Corrected')

	SELECT @vNotInspected = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType NOT IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType --AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallInspectionStatusId IN (SELECT InspectionStatusId FROM dbo.InspectionStatus WHERE Status = 'Not Inspected')

	SELECT @vInspectionReady = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType NOT IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType --AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallInspectionStatusId IN (SELECT InspectionStatusId FROM dbo.InspectionStatus WHERE Status = 'Ready for Inspection')

	SELECT @vInspectionInProgress = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType NOT IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType --AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallInspectionStatusId IN (SELECT InspectionStatusId FROM dbo.InspectionStatus WHERE Status = 'In Progress')

	SELECT @vInspectionApproved30Days = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType NOT IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallInspectionStatusId IN (SELECT InspectionStatusId FROM dbo.InspectionStatus WHERE Status = 'Approved')

	SELECT @vInspectionRejected = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType NOT IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType --AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallInspectionStatusId IN (SELECT InspectionStatusId FROM dbo.InspectionStatus WHERE Status = 'Rejected')

	SELECT @vNotInvoiced = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType NOT IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType --AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallInvoiceStatusId IN (SELECT InvoiceStatusId FROM dbo.InvoiceStatus WHERE Description = 'Not Invoiced')

	SELECT @vInvoiceSubmitted = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType NOT IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType --AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallInvoiceStatusId IN (SELECT InvoiceStatusId FROM dbo.InvoiceStatus WHERE Description = 'Submitted')

	SELECT @vInvoiceApproved = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType NOT IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType --AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallInvoiceStatusId IN (SELECT InvoiceStatusId FROM dbo.InvoiceStatus WHERE Description = 'Approved')

	SELECT @vInvoiceRejected = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType NOT IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType --AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallInvoiceStatusId IN (SELECT InvoiceStatusId FROM dbo.InvoiceStatus WHERE Description = 'Rejected')

	SELECT @vWOCompleted30Days = COUNT(*) FROM [dbo].[WorkFlow] 
		Where ServiceOrderType NOT IN (SELECT AssemblyGuid FROM VendorPortal.dbo.RateGroupPrice WHERE AssemblySource = 'CUST') 
		AND WorkGroup = @vVendorType AND WorkEventDt > DATEADD(Day, -30, getdate())
		AND OverallAsBuiltStatusId IN (SELECT AsBuiltStatusId FROM dbo.AsBuiltStatus WHERE Description = 'Completed')
		AND OverallInspectionStatusId IN (SELECT InspectionStatusId FROM dbo.InspectionStatus WHERE Status = 'Approved')
		AND OverallInvoiceStatusId IN (SELECT InvoiceStatusId FROM dbo.InvoiceStatus WHERE Description IN ('Approved','Exported','Imported'))

    INSERT INTO dbo.AGStakingSheet
	(Total30Days,
	 VendorType,
	 AsBuiltNotStarted,
	 AsBuiltInProgress,
	 AsBuiltCompleted30Days,
	 AsBuiltRejected,
	 AsBuiltAppealed,
	 AsBuiltCorrected,
	 NotInspected,
	 InspectionReady,
	 InspectionInProgress,
	 InspectionApproved30Days,
	 InspectionRejected,
	 NotInvoiced,
	 InvoiceSubmitted,
	 InvoiceApproved,
	 InvoiceRejected,
	 WOCompleted30Days,
	 LastSynced) 
	VALUES 
	(@vTotal30Days,
	 @vVendorType,
	 @vAsBuiltNotStarted,
	 @vAsBuiltInProgress,
	 @vAsBuiltCompleted30Days,
	 @vAsBuiltRejected,
	 @vAsBuiltAppealed,
	 @vAsBuiltCorrected,
	 @vNotInspected,
	 @vInspectionReady,
	 @vInspectionInProgress,
	 @vInspectionApproved30Days,
	 @vInspectionRejected,
	 @vNotInvoiced,
	 @vInvoiceSubmitted,
	 @vInvoiceApproved,
	 @vInvoiceRejected,
	 @vWOCompleted30Days,
	 @vLastSynced)
END

GO
/****** Object:  StoredProcedure [dbo].[NEW_INSPECTION]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE procedure [dbo].[NEW_INSPECTION]
	@IN_InspectionDT Datetime,
	@IN_InspectedBy varchar(50),
	@IN_InspectionType varchar(2),
	@IN_InspectionStatusID int,
	@IN_COMMENTS varchar(512) = null,
	@IN_WORKORDER_ID varchar(50),
	@OUT_Inspection_ID int OUTPUT
 AS
 BEGIN
	SET NOCOUNT ON

	DECLARE @vWorkFlowId int, @vServiceOrderId varchar(50)
	
	SELECT @vWorkFlowId = WorkFlowId, @vServiceOrderId = ServiceOrderId FROM dbo.WorkFlow WHERE WorkOrderId = @IN_WORKORDER_ID 

    insert into dbo.Inspection(InspectionDT, InspectedBy, InspectionStatusID, InspectionType, Comments, WorkOrderId, WorkFlowId, ServiceOrderId)
    values (@IN_InspectionDT, @IN_InspectedBy, @IN_InspectionStatusID, @IN_InspectionType, @IN_COMMENTS, @IN_WORKORDER_ID, @vWorkFlowId, @vServiceOrderId);

	SELECT @OUT_Inspection_ID = SCOPE_IDENTITY();

 END


GO
/****** Object:  StoredProcedure [dbo].[NEW_INSPECTION_DETAIL_MARK_FOR_DEATH]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE procedure [dbo].[NEW_INSPECTION_DETAIL_MARK_FOR_DEATH]
	@IN_StationID varchar(50),
	@IN_AssembleUnitID int,
	@IN_InspectionStatusID int,
	@IN_InspectionID int,
	@IN_COMMENTS varchar(512) = null,
	@OUT_Inspection_Detail_ID int OUTPUT
 AS
 BEGIN
	SET NOCOUNT ON

    insert into dbo.InspectionDetail(StationId, AssembleUnitId, InspectionStatusId, InspectionId, Comment)
    values (@IN_StationID, @IN_AssembleUnitID, @IN_InspectionStatusID, @IN_InspectionID, @IN_COMMENTS);

	SELECT @OUT_Inspection_Detail_ID = SCOPE_IDENTITY();

 END



GO
/****** Object:  StoredProcedure [dbo].[RunAggregateSync]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[RunAggregateSync] AS

BEGIN

	EXEC [dbo].[NEW_AGGREGATE_SYNC_INSP] @vVendorType = 'PIKE'
	EXEC [dbo].NEW_AGGREGATE_SYNC_INV @vVendorType = 'PIKE'
	EXEC [dbo].NEW_AGGREGATE_SYNC_SL @vVendorType = 'PIKE'
	EXEC [dbo].NEW_AGGREGATE_SYNC_SS @vVendorType = 'PIKE'

	EXEC [dbo].[NEW_AGGREGATE_SYNC_INSP] @vVendorType = 'MASTEC'
	EXEC [dbo].NEW_AGGREGATE_SYNC_INV @vVendorType = 'MASTEC'
	EXEC [dbo].NEW_AGGREGATE_SYNC_SL @vVendorType = 'MASTEC'
	EXEC [dbo].NEW_AGGREGATE_SYNC_SS @vVendorType = 'MASTEC'

END
GO
/****** Object:  StoredProcedure [dbo].[UPDATE_INVOICE_STATUS]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =======================================================================
-- Author:		Patrick Bowles
-- Create date: June 25, 2018
-- Description:	Update Invoice Status and Overall statuses for WorkOrder
-- =======================================================================
CREATE PROCEDURE [dbo].[UPDATE_INVOICE_STATUS] 
	-- Add the parameters for the stored procedure here
	@IN_InvoiceId int,
	@IN_InvoiceStatus int,
	@IN_InvoiceType varchar(2),
	@IN_ApprovedBy varchar(50),
	@IN_ApprovedDt datetime,
	@IN_ApprovedComment varchar(256),
	@OUT_Response int OUTPUT
AS
BEGIN
	
	DECLARE @ErrMsg nvarchar(4000), 
			@ErrSeverity int,
			--@SSD_TotalCount int,
			@vTotalNotInvoiced int,
			@SSD_RejectedCount int,
			@VCH_RejectedCount int,
			@vStakingSheetId int,
			@vWorkFlowId int

	SET NOCOUNT ON;

	IF @IN_InvoiceType = 'SS'
		BEGIN
			BEGIN TRY
				BEGIN TRANSACTION
					
					SELECT @vWorkFlowId = WorkFlowId FROM dbo.Invoice WHERE InvoiceId = @IN_InvoiceId
					SELECT @vStakingSheetId = StakingSheetId FROM dbo.StakingSheetDetail WHERE InvoiceId = @IN_InvoiceId GROUP BY StakingSheetId
					--SELECT @SSD_TotalCount = COUNT(InvoiceId) FROM dbo.StakingSheetDetail WHERE InvoiceId = @IN_InvoiceId
					SELECT @vTotalNotInvoiced = COUNT(InvoiceId) FROM dbo.StakingSheetDetail WHERE StakingSheetId = @vStakingSheetId 
						AND InvoiceStatusId IN (SELECT InvoiceStatusId FROM dbo.InvoiceStatus WHERE Description <> 'Approved' AND Description <> 'Submitted')--and InvoiceId IS NULL
					SELECT @SSD_RejectedCount = COUNT(InvoiceId) FROM dbo.StakingSheetDetail WHERE InvoiceId = @IN_InvoiceId 
						AND InvoiceStatusId = (SELECT InvoiceStatusId FROM dbo.InvoiceStatus WHERE Description = 'Rejected')
					SELECT @VCH_RejectedCount = COUNT(InvoiceId) FROM dbo.Voucher WHERE InvoiceId = @IN_InvoiceId 
						AND InvoiceStatusId = (SELECT InvoiceStatusId FROM dbo.InvoiceStatus WHERE Description = 'Rejected')

					IF (@IN_InvoiceStatus = 4) --Approved
						BEGIN
							IF (@SSD_RejectedCount = 0 AND @VCH_RejectedCount = 0)--None are Rejected
								BEGIN

									-- Update Invoice Status and overall statuses onWorkFlow Tbl, ready for export
									UPDATE dbo.Invoice
									SET InvoiceStatusId = @IN_InvoiceStatus,
										ApprovedBy = @IN_ApprovedBy,
										ApprovedDt = @IN_ApprovedDt,
										ApprovedComment = @IN_ApprovedComment
									WHERE InvoiceId = @IN_InvoiceId

									IF (@vTotalNotInvoiced > 0)
										BEGIN
											UPDATE dbo.WorkFlow
											SET OverallInvoiceStatusId = (SELECT InvoiceStatusId FROM dbo.InvoiceStatus WHERE Description = 'Partially Invoiced')
											WHERE WorkFlowId = @vWorkFlowId
										END
									ELSE
										BEGIN
										
											UPDATE dbo.WorkFlow
											SET OverallInvoiceStatusId = @IN_InvoiceStatus
											WHERE WorkFlowId = @vWorkFlowId

										END
								END	
							ELSE --Some or One is failed
								BEGIN
									--Should Never Happen if it does, error on GUI Side
									
									SET @OUT_Response = '1'
								END
						END
					ELSE IF (@IN_InvoiceStatus = 3)--Rejected
						IF (@SSD_RejectedCount = 0 AND @VCH_RejectedCount = 0)
								--All Detail records have passed, header was rejected
								BEGIN
								
									--Update Invoice record
									UPDATE dbo.Invoice
									SET InvoiceStatusId = @IN_InvoiceStatus,
										ApprovedBy = @IN_ApprovedBy,
										ApprovedDt = @IN_ApprovedDt,
										ApprovedComment = @IN_ApprovedComment
									WHERE InvoiceId = @IN_InvoiceId

									--Update SSD record to "failed ready to re-invoice"
									UPDATE dbo.StakingSheetDetail
									SET InvoiceStatusId = 1 --Need to figure out the right Status or add new status-----------------------------------------
									WHERE InvoiceId = @IN_InvoiceId

									UPDATE dbo.Voucher
									SET InvoiceStatusId = 1 --Need to figure out the right Status or add new status-----------------------------------------
									WHERE InvoiceId = @IN_InvoiceId

									--Update WorkFlow record overall inv status = rejected
									UPDATE dbo.WorkFlow
									SET OverallInvoiceStatusId = 3 --Rejected
									WHERE WorkFlowId = @vWorkFlowId
								END	
							ELSE IF (@SSD_RejectedCount > 0 OR @VCH_RejectedCount > 0)
								--One or Some detail records are rejected
								BEGIN
									
									--Update Invoice record
									UPDATE dbo.Invoice
									SET InvoiceStatusId = @IN_InvoiceStatus,
										ApprovedBy = @IN_ApprovedBy,
										ApprovedDt = @IN_ApprovedDt,
										ApprovedComment = @IN_ApprovedComment
									WHERE InvoiceId = @IN_InvoiceId

									--for all rejected records change AsBuilt, Inspection, and Invoice to rejected
									UPDATE dbo.StakingSheetDetail 
									SET AsBuiltStatusId = 5, --Rejected----------------------------------------------------------------
										CurrentInspectionDetailStatusId = 5, --Rejected------------------------------------------------
										InvoiceStatusId = 3
									WHERE InvoiceId = @IN_InvoiceId AND InvoiceStatusId = 3

									UPDATE dbo.Voucher
									SET InspectionStatusId = 5, --Rejected------------------------------------------------
										InvoiceStatusId = 3 --Need to figure out the right Status or add new status-----------------------------------------
									WHERE InvoiceId = @IN_InvoiceId

									--for all Approved records change InvoiceStatus to failed ready to re-invoice
									UPDATE dbo.StakingSheetDetail
									SET InvoiceStatusId = 1 --ReadytoInvoice--------------------------------------------------------------
									WHERE InvoiceId = @IN_InvoiceId AND InvoiceStatusId <> 3

									UPDATE dbo.Voucher
									SET InvoiceStatusId = 1 --ReadytoInvoice--------------------------------------------------------------
									WHERE InvoiceId = @IN_InvoiceId AND InvoiceStatusId <> 3

									--Overall for AsBuilt, Inspection, Invoice to Rejected
									UPDATE dbo.WorkFlow
									SET OverallInvoiceStatusId = 3,
										OverallAsBuiltStatusId = 5,
										OverallInspectionStatusId = 1
									WHERE WorkFlowId = @vWorkFlowId

								END

				COMMIT TRANSACTION
				SET @OUT_Response = '1'
			END TRY
			BEGIN CATCH
				ROLLBACK TRANSACTION

				--Update SSD records to error/cancelled for user to try again
				UPDATE dbo.StakingSheetDetail
				SET InvoiceStatusId = 1 --Need to figure out the right Status or add new status-----------------------------------------------
				WHERE InvoiceId = @IN_InvoiceId

				UPDATE dbo.Voucher
				SET InvoiceStatusId = 1 --Need to figure out the right Status or add new status-----------------------------------------
				WHERE InvoiceId = @IN_InvoiceId

				  SELECT @ErrMsg = ERROR_MESSAGE(),
					@ErrSeverity = ERROR_SEVERITY()

				  RAISERROR(@ErrMsg, @ErrSeverity, 1)

			END CATCH
		END
	ELSE IF @IN_InvoiceType = 'SL'
		BEGIN
			BEGIN TRY
				BEGIN TRANSACTION

					IF (@IN_InvoiceStatus = 4) --Approved
						BEGIN

							UPDATE dbo.Invoice
							SET InvoiceStatusId = @IN_InvoiceStatus,
								ApprovedBy = @IN_ApprovedBy,
								ApprovedDt = @IN_ApprovedDt,
								ApprovedComment = @IN_ApprovedComment
							WHERE InvoiceId = @IN_InvoiceId

						END
					ELSE --Rejected
						BEGIN

							UPDATE dbo.Invoice
							SET InvoiceStatusId = @IN_InvoiceStatus,
								ApprovedBy = @IN_ApprovedBy,
								ApprovedDt = @IN_ApprovedDt,
								ApprovedComment = @IN_ApprovedComment
							WHERE InvoiceId = @IN_InvoiceId

							UPDATE dbo.ServiceOrder
							SET InvoiceStatusId = 1 --Not Invoiced
							WHERE InvoiceId = @IN_InvoiceId AND InvoiceStatusId = 2 --Submitted

							UPDATE dbo.ServiceOrder
							SET InspectionStatusId = 5, --Rejected
								InvoiceStatusId = 1
							WHERE InvoiceId = @IN_InvoiceId AND InvoiceStatusId = 3 --Rejected

						END


				COMMIT TRANSACTION
				SET @OUT_Response = '2'
			END TRY
			BEGIN CATCH
				ROLLBACK TRANSACTION

				--Update SSD records to error/cancelled for user to try again

				  SELECT @ErrMsg = ERROR_MESSAGE(),
					@ErrSeverity = ERROR_SEVERITY()

				  RAISERROR(@ErrMsg, @ErrSeverity, 1)

			END CATCH
		END

	ELSE
		BEGIN
		SET @OUT_Response = '0'
		END

END

GO
/****** Object:  StoredProcedure [dbo].[UPDATE_OVERALL_ASBUILT_STATUS]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- ==========================================================
-- Author:		Patrick Bowles
-- Create date: June 22, 2018
-- Description:	Update Overall AsBuilt Status on the WorkFlow table
-- ==========================================================
CREATE PROCEDURE [dbo].[UPDATE_OVERALL_ASBUILT_STATUS] 
	-- Add the parameters for the stored procedure here
	@IN_WorkOrderId varchar(15),
	@OUT_Response int OUTPUT
AS
BEGIN
	
	DECLARE @vStakingSheetId varchar(50),
			@vRemaining int,
			@vAppealed int,
			@vCorrected int,
			@vVendorId int

	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	SELECT @vStakingSheetId = StakingSheetId FROM dbo.StakingSheet where WorkOrderId = @IN_WorkOrderId
	
	SELECT @vRemaining = count(*) FROM dbo.StakingSheetDetail ssd 
	LEFT OUTER JOIN dbo.StakingSheet ss ON ss.StakingSheetId = ssd.StakingSheetId
	where ss.WorkOrderId = @IN_WorkOrderId AND AsBuiltStatusId <> 3 --completed

	SELECT @vAppealed = count(*) FROM dbo.StakingSheetDetail ssd 
	LEFT OUTER JOIN dbo.StakingSheet ss ON ss.StakingSheetId = ssd.StakingSheetId
	where ss.WorkOrderId = @IN_WorkOrderId AND AsBuiltStatusId = 6 -- Appealed

	SELECT @vCorrected = count(*) FROM dbo.StakingSheetDetail ssd 
	LEFT OUTER JOIN dbo.StakingSheet ss ON ss.StakingSheetId = ssd.StakingSheetId
	where ss.WorkOrderId = @IN_WorkOrderId AND AsBuiltStatusId = 7 --corrected


	IF @vRemaining > 0 -- there are still AU not AsBuilt complete
		BEGIN
			
			IF @vAppealed > 0 -- if there are appealed AU's
				BEGIN

					UPDATE dbo.StakingSheetDetail
						SET CurrentInspectionDetailStatusId = 2
					WHERE StakingSheetId = @vStakingSheetId AND AsBuiltStatusId = 6

				END
			IF @vCorrected > 0 -- if there are corrected AU's
				BEGIN

					UPDATE dbo.StakingSheetDetail
						SET CurrentInspectionDetailStatusId = 2
					WHERE StakingSheetId = @vStakingSheetId AND AsBuiltStatusId = 7

				END


			UPDATE dbo.WorkFlow
			SET OverallAsBuiltStatusId = 2--, --In Progress
				--OverallInspectionStatusId = 3
			WHERE WorkOrderId = @IN_WorkOrderId

			SET @OUT_Response = 0

		END
	ELSE 
		BEGIN

			UPDATE dbo.WorkFlow
			SET OverallAsBuiltStatusId = 3, --completed
				OverallInspectionStatusId = 2
			WHERE WorkOrderId = @IN_WorkOrderId

			SELECT @OUT_Response = WorkFlowTaskId FROM dbo.WorkFlowTask wft
			LEFT OUTER JOIN dbo.WorkFlow wf ON wf.WorkFlowId = wft.WorkFlowId AND wf.WorkGroup = wft.WorkGroup
			WHERE wf.WorkOrderId = @IN_WorkOrderId

			UPDATE dbo.WorkFlowTask
			SET WorkEventStatusId = 'COMP',
				WorkOrderId = 'COMP'
			WHERE WorkFlowTaskId = @OUT_Response


			--SET @OUT_Response = 1

		END

END
GO
/****** Object:  StoredProcedure [dbo].[UPDATE_OVERALL_WORKFLOW_STATUS]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- ==========================================================
-- Author:		Patrick Bowles
-- Create date: June 13, 2018
-- Description:	Update the Workflow Record with Overall status 
-- ==========================================================
CREATE PROCEDURE [dbo].[UPDATE_OVERALL_WORKFLOW_STATUS]
@IN_WorkOrderId varchar(15),
@IN_AsBuiltStatusId int,
@IN_InspectionStatusId int,
@IN_InvoiceId int,
@IN_PaymentStatusId int
AS
BEGIN
	SET NOCOUNT ON;

	IF @IN_AsBuiltStatusId IS NOT NULL
		BEGIN
			UPDATE dbo.WorkFlow
			SET OverallAsBuiltStatusId = @IN_AsBuiltStatusId
			WHERE WorkOrderId = @IN_WorkOrderId
		END

	IF @IN_InspectionStatusId IS NOT NULL
		BEGIN
			UPDATE dbo.WorkFlow
			SET OverallInspectionStatusId = @IN_InspectionStatusId
			WHERE WorkOrderId = @IN_WorkOrderId
		END

	IF @IN_InvoiceId IS NOT NULL
		BEGIN
			UPDATE dbo.WorkFlow
			SET OverallInvoiceStatusId = @IN_InvoiceId
			WHERE WorkOrderId = @IN_WorkOrderId
		END

	IF @IN_PaymentStatusId IS NOT NULL
		BEGIN
			UPDATE dbo.WorkFlow
			SET OverallPaymentStatusId = @IN_PaymentStatusId
			WHERE WorkOrderId = @IN_WorkOrderId
		END

END
GO
/****** Object:  StoredProcedure [dbo].[UPDATE_SO_INVOICE_STATUS]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- ==========================================================================
-- Author:		Patrick Bowles
-- Create date: September 26, 2018
-- Description:	Update SO Invoice Status and Overall statuses for WorkOrder
-- ==========================================================================
CREATE PROCEDURE [dbo].[UPDATE_SO_INVOICE_STATUS] 
	-- Add the parameters for the stored procedure here
	@IN_InvoiceId int,
	@IN_InvoiceStatus int,
	@IN_InvoiceType varchar(2),
	@OUT_Response int OUTPUT
AS
BEGIN
	
	DECLARE @ErrMsg nvarchar(4000), 
			@ErrSeverity int,
			@vAUNotInCurrentStatus int,
			@vAUNotPaid int,
			@vSSID int,
			@vWFID int

	SET NOCOUNT ON;

	IF @IN_InvoiceType = 'SS'
		BEGIN

			UPDATE dbo.Invoice
			SET InvoiceStatusId = @IN_InvoiceStatus
			WHERE InvoiceId = @IN_InvoiceId

			UPDATE dbo.StakingSheetDetail
			SET InvoiceStatusId = @IN_InvoiceStatus
			WHERE InvoiceId = @IN_InvoiceId

			SELECT @vSSID = StakingSheetID FROM dbo.StakingSheetDetail WHERE InvoiceId = @IN_InvoiceId
			SELECT @vWFID = WorkFlowId FROM dbo.StakingSheet WHERE StakingSheetId = @vSSID

			--Are all AU in paid status? get count of au not in status 12(PAID)
			SELECT @vAUNotPaid = COUNT(*) FROM StakingSheetDetail 
			WHERE StakingSheetId = (SELECT StakingSheetID FROM dbo.StakingSheetDetail WHERE InvoiceId = @IN_InvoiceId)
			AND InvoiceStatusId <> 12

			IF @vAUNotPaid > 0 -- if all AU are paid then overallinvoicestatus = 12
				BEGIN

					UPDATE dbo.WorkFlow
					SET OverallInvoiceStatusId = 12
					WHERE WorkFlowId = @vWFID

				END
			ELSE -- else set overallinvoicestatus to partially paid = 10
				BEGIN
					
					UPDATE dbo.WorkFlow
					SET OverallInvoiceStatusId = 5
					WHERE WorkFlowId = @vWFID

				END

		END
	ELSE IF @IN_InvoiceType = 'SL'
		BEGIN

			UPDATE dbo.Invoice
			SET InvoiceStatusId = @IN_InvoiceStatus
			WHERE InvoiceId = @IN_InvoiceId

			UPDATE dbo.ServiceOrder
			SET InvoiceStatusId = @IN_InvoiceStatus
			WHERE InvoiceId = @IN_InvoiceId

		END

END

GO
/****** Object:  StoredProcedure [dbo].[UPDATE_STAKING_ASBUILTAMOUNT]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- ==========================================================
-- Author:		Patrick Bowles
-- Create date: December 12, 2018
-- Description:	Update Staking Sheet AsBuiltAmount
-- ==========================================================
CREATE PROCEDURE [dbo].[UPDATE_STAKING_ASBUILTAMOUNT] 
	-- Add the parameters for the stored procedure here
	@IN_StakingSheetDetailID varchar(50),
	@IN_WorkEventDt datetime,
	@OUT_Response int OUTPUT
AS
BEGIN
	
	DECLARE @vAsBuiltQuantity int,
			@vAssemblyGuid varchar(50),
			@vAssemblyRateGroupId int,
			@vAssemblyActionCode varchar(3),
			@vAssemblyAmount decimal(18,2)

	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	SELECT  @vAsBuiltQuantity = AsBuiltQuantity, 
			@vAssemblyGuid = AssemblyGuid,
			@vAssemblyRateGroupId = AssemblyRateGroupId,
			@vAssemblyActionCode = AssemblyActionCode
	FROM dbo.StakingSheetDetail WHERE StakingSheetDetailId = @IN_StakingSheetDetailID

	SELECT @vAssemblyAmount =
		CASE @vAssemblyActionCode 
			WHEN 'C' THEN (LaborConstCost * LaborConstHours) 
			WHEN 'R' THEN (LaborRetireCost * LaborRetireHours)

			END
			
	FROM dbo.RateGroupPrice WHERE AssemblyGuid = @vAssemblyGuid AND RateGroupId = @vAssemblyRateGroupId
	AND @IN_WorkEventDt BETWEEN EffectiveStartDt AND EffectiveEndDt

	UPDATE dbo.StakingSheetDetail
		SET AsBuiltAmount = @vAssemblyAmount * @vAsBuiltQuantity
	WHERE StakingSheetDetailId = @IN_StakingSheetDetailID

	SET @OUT_Response = 1
	
END
GO
/****** Object:  StoredProcedure [dbo].[UPDATE_SUBMIT_INVOICE]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- ==========================================================
-- Author:		Patrick Bowles
-- Create date: June 5, 2018
-- Description:	Submit the Invoice to be ready for the Export
--   for both StakingSheet and SteetLight
-- Last Modified:  January 29, 2019
-- ==========================================================
CREATE PROCEDURE [dbo].[UPDATE_SUBMIT_INVOICE] 
	-- Add the parameters for the stored procedure here
	@IN_GUID Uniqueidentifier,
	@IN_InvoiceType varchar(2),
	@IN_InvoicedBy varchar(50),
	@IN_WorkEventDt datetime,
	@IN_VendorReference varchar(40),
	@OUT_InvoiceId int OUTPUT
AS
BEGIN
	
	DECLARE @vInvoiceAmount decimal(18,2),
			@vInvoiceConstruct decimal(18,2),
			@vInvoiceRetire decimal(18,2),
			@vVendorId int,
			@vServiceOrder varchar(50),
			@vWorkFlow varchar(50),
			@vWorkOrder varchar(50),
			@vInvoiceDetailId int,
			@vStakingSheetId int,
			@vTotalStakingSheetDetailsNull int,
			@vWorkFlowId int,
			@vDesignCost decimal(18,2),
			@vDesignConstruct decimal(18,2),
			@vDesignRetire decimal(18,2),
			@vDesignUniqueAssembly int,
			@vDesignTotalAssembly int,
			@vDesignTotalStation int,
			@vInvoiceUniqueAssembly int,
			@vInvoiceTotalAssembly int,
			@vInvoiceTotalStation int,
			@vCurrentDate datetime,
			@vSSDCount int,
			@vVoucherCount int,
			@vVoucherQty int,
			@vVoucherConst decimal(18,2),
			@vVoucherRetire decimal(18,2),
			@vVoucherSplitConst decimal(18,2),
			@vVoucherSplitRetire decimal(18,2),
			@vVoucherAmount decimal(18,2),
			@vVouchersNull int

	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	SET @vCurrentDate = GETDATE()

	IF @IN_InvoiceType = 'SS'
		BEGIN
		
		  --Get all staking sheet detail records with the GUID
			   --Get RateGroup Values for each assemblyUnit
   			   --foreach AU * quantity add to line total and SUM the total

		SELECT @vSSDCount = count(*) FROM dbo.StakingSheetDetail WHERE InvoiceSubmitGuid = @IN_GUID
		SELECT @vVoucherCount = count(*) FROM dbo.Voucher WHERE SubmitGuid = @IN_GUID
		  
		SELECT @vStakingSheetId = ss.StakingSheetId
			,@vWorkFlow = ss.WorkFlowId
			,@vWorkOrder = ss.WorkOrderId
			,@vServiceOrder = ss.ServiceOrderId
			,@vVendorId = rg.VendorId
		  FROM [VendorPortal].[dbo].[StakingSheetDetail] ssd
		  LEFT OUTER JOIN dbo.RateGroupPrice rgp ON rgp.AssemblyGuid = ssd.AssemblyGuid and rgp.RateGroupId = ssd.AssemblyRateGroupId
		  LEFT OUTER JOIN dbo.RateGroup rg ON rg.RateGroupId = ssd.AssemblyRateGroupId
		  LEFT OUTER JOIN dbo.StakingSheet ss ON ss.StakingSheetId = ssd.StakingSheetId
		  LEFT OUTER JOIN dbo.Voucher v ON v.StakingSheetId = ss.StakingSheetId
		  WHERE ssd.InvoiceSubmitGuid = @IN_GUID
		  OR v.SubmitGuid = @IN_GUID
		  AND @IN_WorkEventDt BETWEEN rgp.EffectiveStartDt AND rgp.EffectiveEndDt
		  GROUP BY ss.StakingSheetId, ss.WorkFlowId, ss.WorkOrderId, ss.ServiceOrderId, rg.VendorId

		SELECT @vDesignConstruct = isnull(SUM(ssd.AssemblyQuantity * CAST((rgp.LaborConstCost * rgp.LaborConstHours) AS decimal(10,2))), 0)
			FROM dbo.StakingSheetDetail ssd 
			LEFT OUTER JOIN dbo.RateGroupPrice rgp ON rgp.AssemblyGuid = ssd.AssemblyGuid AND rgp.RateGroupId = ssd.AssemblyRateGroupId
			WHERE InvoiceSubmitGuid = @IN_GUID AND AssemblyQuantity > 0 
			AND @IN_WorkEventDt BETWEEN rgp.EffectiveStartDt AND rgp.EffectiveEndDt
			AND ssd.AssemblyActionCode = 'C'

		SELECT @vDesignRetire = isnull(SUM(ssd.AssemblyQuantity * CAST((rgp.LaborRetireCost * rgp.LaborRetireHours) AS decimal(10,2))), 0)
			FROM dbo.StakingSheetDetail ssd 
			LEFT OUTER JOIN dbo.RateGroupPrice rgp ON rgp.AssemblyGuid = ssd.AssemblyGuid AND rgp.RateGroupId = ssd.AssemblyRateGroupId
			WHERE InvoiceSubmitGuid = @IN_GUID AND AssemblyQuantity > 0 
			AND @IN_WorkEventDt BETWEEN rgp.EffectiveStartDt AND rgp.EffectiveEndDt
			AND ssd.AssemblyActionCode = 'R'

		SELECT 	@vDesignTotalStation = isnull(count(DISTINCT ssd.StationDescription),0),
			@vDesignUniqueAssembly = isnull(count(DISTINCT ssd.AssemblyGuid),0),
			@vDesignTotalAssembly = isnull(SUM(ssd.AssemblyQuantity),0)
			FROM dbo.StakingSheetDetail ssd 
			LEFT OUTER JOIN dbo.RateGroupPrice rgp ON rgp.AssemblyGuid = ssd.AssemblyGuid AND rgp.RateGroupId = ssd.AssemblyRateGroupId
			WHERE InvoiceSubmitGuid = @IN_GUID AND AssemblyQuantity > 0
			AND @IN_WorkEventDt BETWEEN rgp.EffectiveStartDt AND rgp.EffectiveEndDt

		SELECT @vInvoiceConstruct = isnull(SUM(ssd.AsBuiltQuantity * CAST(rgp.LaborConstCost * rgp.LaborConstHours AS decimal(10,2))), 0)
			FROM dbo.StakingSheetDetail ssd 
			LEFT OUTER JOIN dbo.RateGroupPrice rgp ON rgp.AssemblyGuid = ssd.AssemblyGuid AND rgp.RateGroupId = ssd.AssemblyRateGroupId
			WHERE InvoiceSubmitGuid = @IN_GUID
			AND @IN_WorkEventDt BETWEEN rgp.EffectiveStartDt AND rgp.EffectiveEndDt
			AND ssd.AssemblyActionCode = 'C'

		SELECT @vInvoiceRetire = isnull(SUM(ssd.AsBuiltQuantity * CAST(rgp.LaborRetireCost * rgp.LaborRetireHours AS decimal(10,2))), 0)
			FROM dbo.StakingSheetDetail ssd 
			LEFT OUTER JOIN dbo.RateGroupPrice rgp ON rgp.AssemblyGuid = ssd.AssemblyGuid AND rgp.RateGroupId = ssd.AssemblyRateGroupId
			WHERE InvoiceSubmitGuid = @IN_GUID
			AND @IN_WorkEventDt BETWEEN rgp.EffectiveStartDt AND rgp.EffectiveEndDt
			AND ssd.AssemblyActionCode = 'R'

		SELECT 	@vInvoiceTotalStation = isnull(count(DISTINCT ssd.StationDescription),0),
			@vInvoiceUniqueAssembly = isnull(count(DISTINCT ssd.AssemblyGuid),0),
			@vInvoiceTotalAssembly = isnull(count(ssd.AssemblyGuid),0)
			FROM dbo.StakingSheetDetail ssd 
			LEFT OUTER JOIN dbo.RateGroupPrice rgp ON rgp.AssemblyGuid = ssd.AssemblyGuid AND rgp.RateGroupId = ssd.AssemblyRateGroupId
			WHERE InvoiceSubmitGuid = @IN_GUID
			AND @IN_WorkEventDt BETWEEN rgp.EffectiveStartDt AND rgp.EffectiveEndDt
		
		-- Total Voucher amount submitted in this invoice.
		SELECT @vVoucherAmount = isnull(SUM(v.Amount), 0) FROM dbo.Voucher v WHERE SubmitGuid = @IN_GUID --, v.GLAccountId, gl.GL_Account
			--LEFT OUTER JOIN dbo.GLAccount gl ON gl.GLAccountId = v.GLAccountId
		
		-- Total Voucher count submitted in this invoice.
		SELECT @vVoucherQty = isnull(count(v.VoucherId), 0) FROM dbo.Voucher v WHERE SubmitGuid = @IN_GUID
			
		--Total Voucher(s) Amount for Construct GL Account
		SELECT @vVoucherConst = isnull(SUM(v.Amount), 0) FROM dbo.Voucher v WHERE GLAccountId = 1 AND GLAccountIdSplit is null AND SubmitGuid = @IN_GUID
		
		--Total Voucher(s) Amount for Retire GL Account
		SELECT @vVoucherRetire = isnull(SUM(v.Amount), 0) FROM dbo.Voucher v WHERE GLAccountId = 3 AND GLAccountIdSplit is null AND SubmitGuid = @IN_GUID
		
		--Total Voucher(s) Amount for Split for Contruct and Retire GL Accounts
		SELECT @vVoucherSplitConst = isnull(ROUND(sum(Amount)/2,2,0),0), @vVoucherSplitRetire = isnull(ROUND(sum(Amount)/2,2,1),0) 
			FROM dbo.Voucher WHERE GLAccountIdSplit is not null AND SubmitGuid = @IN_GUID
			
		SET @vVoucherConst = @vVoucherConst + @vVoucherSplitConst
		SET @vVoucherRetire = @vVoucherRetire + @vVoucherSplitRetire

		SET @vDesignCost = @vDesignConstruct + @vDesignRetire
		SET @vInvoiceAmount = @vInvoiceConstruct + @vInvoiceRetire + @vVoucherAmount
			
			--insert into Invoice table
			INSERT INTO dbo.Invoice
			(InvoiceStatusId, InvoicedBy, InvoicedDt, InvoiceType, InvoiceAmount, VendorId, VendorReference, WorkFlowId, WorkOrderId, ServiceOrderId,
			DesignCost, DesignUniqueAssembly, DesignTotalAssembly, DesignTotalStation, InvoiceUniqueAssembly, InvoiceTotalAssembly, InvoiceTotalStation,
			InvoiceVoucherQty, InvoiceVoucherCost)
			VALUES
			(1, @IN_InvoicedBy, @vCurrentDate, @IN_InvoiceType, @vInvoiceAmount, @vVendorId, @IN_VendorReference, @vWorkFlow, @vWorkOrder, @vServiceOrder,
			@vDesignCost, @vDesignUniqueAssembly, @vDesignTotalAssembly, @vDesignTotalStation, @vInvoiceUniqueAssembly, @vInvoiceTotalAssembly, @vInvoiceTotalStation,
			@vVoucherQty, @vVoucherAmount)

			SELECT @OUT_InvoiceId = SCOPE_IDENTITY()

			IF @vInvoiceConstruct > 0.00 OR @vVoucherConst > 0.00
				BEGIN

					--Get GlAccount Information
					--Insert into InvoiceDetail summarized value
					INSERT INTO dbo.InvoiceDetail
						(InvoiceId, 
						 Description, 
						 Amount, 
						 GL_Code,
						 GLDepartment,
						 GLActivity)
					VALUES
						(@OUT_InvoiceId, 
						(SELECT GL_Description FROM dbo.GLAccount where GLAccountId = 1), --107.2 Name
						@vInvoiceConstruct + @vVoucherConst, 
						(SELECT GL_Account FROM dbo.GLAccount where GLAccountId = 1),  --107.2
						(SELECT GL_Department FROM dbo.GLAccount where GLAccountId = 1),
						(SELECT GL_Activity FROM dbo.GLAccount where GLAccountId = 1))

					SELECT @vInvoiceDetailId = SCOPE_IDENTITY()

					--Update SSD with InvoiceId, InvoiceDetailId and NULL for SubmitGuid
					UPDATE dbo.StakingSheetDetail
					SET InvoiceId = @OUT_InvoiceId,
						InvoiceDetailId = @vInvoiceDetailId,
						InvoiceStatusId = '2',
						InvoiceSubmitGuid = NULL
					WHERE InvoiceSubmitGuid = @IN_GUID
					AND AssemblyActionCode = 'C'

					UPDATE dbo.Voucher
					SET InvoiceId = @OUT_InvoiceId,
						InvoiceDetailId = @vInvoiceDetailId,
						InvoiceStatusId = '2',
						SubmitGuid = NULL
					WHERE SubmitGuid = @IN_GUID
					AND GLAccountId = 1
				END


			IF @vInvoiceRetire > 0.00 OR @vVoucherRetire >0.00
				BEGIN

					--Get GlAccount Information
					--Insert into InvoiceDetail summarized value
					INSERT INTO dbo.InvoiceDetail
						(InvoiceId, 
						 Description, 
						 Amount, 
						 GL_Code,
						 GLDepartment,
						 GLActivity)
					VALUES
						(@OUT_InvoiceId, 
						(SELECT GL_Description FROM dbo.GLAccount where GLAccountId = 3),  --108.08
						@vInvoiceRetire + @vVoucherRetire, 
						(SELECT GL_Account FROM dbo.GLAccount where GLAccountId = 3),  --108.08
						(SELECT GL_Department FROM dbo.GLAccount where GLAccountId = 3),
						(SELECT GL_Activity FROM dbo.GLAccount where GLAccountId = 3))

					SELECT @vInvoiceDetailId = SCOPE_IDENTITY()

					--Update SSD with InvoiceId, InvoiceDetailId and NULL for SubmitGuid
					UPDATE dbo.StakingSheetDetail
					SET InvoiceId = @OUT_InvoiceId,
						InvoiceDetailId = @vInvoiceDetailId,
						InvoiceStatusId = '2',
						InvoiceSubmitGuid = NULL
					WHERE InvoiceSubmitGuid = @IN_GUID
					AND AssemblyActionCode = 'R'

					UPDATE dbo.Voucher
					SET InvoiceId = @OUT_InvoiceId,
						InvoiceDetailId = @vInvoiceDetailId,
						InvoiceStatusId = 2,
						SubmitGuid = NULL
					WHERE SubmitGuid = @IN_GUID
					AND GLAccountId = 3

				END

			--Update the Invoice Record status to 'SUBMITTED'
			UPDATE dbo.Invoice SET InvoiceStatusId = 2 WHERE InvoiceId = @OUT_InvoiceId

			--Update the Over All Invoice Status on the WorkFlow table
			SELECT @vWorkFlowId = WorkFlowId FROM dbo.StakingSheet WHERE StakingSheetId = @vStakingSheetId

			-- Is there any more StakingSheetdetail records not in Submitted or Approved status
			SELECT @vTotalStakingSheetDetailsNull = count(*) FROM dbo.StakingSheetDetail WHERE StakingSheetId = @vStakingSheetId AND InvoiceStatusId <> 2 OR InvoiceStatusId <> 4

			SELECT @vVouchersNull = count(*) FROM dbo.Voucher v WHERE StakingSheetId = @vStakingSheetId AND InvoiceStatusId <> 2 OR InvoiceStatusId <> 4

			IF @vTotalStakingSheetDetailsNull > 0 OR @vVouchersNull > 0
				BEGIN
					--Still have AU or Vouchers to Invoice
					UPDATE dbo.WorkFlow
					SET OverallInvoiceStatusId = (SELECT InvoiceStatusId FROM dbo.InvoiceStatus WHERE Description = 'Partially Invoiced')
					WHERE WorkFlowId = @vWorkFlowId
				END
			ELSE
				BEGIN
					--All AU's have been invoiced

					UPDATE dbo.WorkFlow
					SET OverallInvoiceStatusId = '2'
					WHERE WorkFlowId = @vWorkFlowId

				END
		END








	ELSE IF @IN_InvoiceType = 'SL'
		BEGIN
			
		SELECT @vVendorId = v.VendorId, @vServiceOrder = so.ServiceOrderId, @vWorkOrder = so.WorkOrderId, @vWorkFlowId = so.WorkFlowId FROM [dbo].[ServiceOrder] so
			  LEFT OUTER JOIN dbo.WorkFlow wf ON wf.ServiceOrderId = so.ServiceOrderId
			  LEFT OUTER JOIN dbo.Vendor v ON v.Name = wf.WorkGroup
		WHERE so.InvoiceSubmitGuid = @IN_GUID
		GROUP BY v.VendorId, so.ServiceOrderId, so.WorkOrderId, so.WorkFlowId

		--insert into Invoice table
			INSERT INTO dbo.Invoice
			(InvoiceStatusId, InvoicedBy, InvoicedDt, InvoiceType, InvoiceAmount, VendorId, VendorReference, WorkFlowId, WorkOrderId, ServiceOrderId)
			VALUES
			(1, @IN_InvoicedBy, @vCurrentDate, @IN_InvoiceType, 0, @vVendorId, @IN_VendorReference, @vWorkFlowId, @vWorkOrder, @vServiceOrder)

			SELECT @OUT_InvoiceId = SCOPE_IDENTITY()

		  --Get Cost per Street light type then calculate the sum for all SO records
		  DECLARE @vGLConstAccountId int,
				  @vGLRetireAccountId int,
				  @vGLDepartment int,
				  @vGLActivity int,
				  @vGLRetireDepartment int,
				  @vGLRetireActivity int,
				  @vGLAccount decimal(10,6),
				  @vGLDescription varchar(40),
				  @vInvoiceDetailAmount decimal(18,2)

		  DECLARE MY_CURSOR CURSOR
			LOCAL STATIC FORWARD_ONLY
		  FOR
		  SELECT DISTINCT rgp.GLConstAccountId, rgp.GLRetireAccountId, rgp.GL_Department, rgp.GLActivity, rgp.GLRetireDepartment, rgp.GLRetireActivity
		  FROM [VendorPortal].[dbo].[ServiceOrder] so
		  LEFT OUTER JOIN VendorPortal.dbo.WorkFlow wf ON wf.ServiceOrderId = so.ServiceOrderId
		  LEFT OUTER JOIN VendorPortal.dbo.RateGroup rg ON rg.RateGroupName = wf.WorkGroup
		  LEFT OUTER JOIN VendorPortal.dbo.RateGroupPrice rgp ON rgp.AssemblyGuid = so.SoTypeCode AND rgp.RateGroupId = rg.RateGroupId
		  WHERE so.InvoiceSubmitGuid = @IN_GUID

		  OPEN MY_CURSOR
		  FETCH NEXT FROM MY_CURSOR INTO  @vGLConstAccountId, @vGLRetireAccountId, @vGLDepartment, @vGLActivity, @vGLRetireDepartment, @vGLRetireActivity
		  WHILE @@FETCH_STATUS = 0
		  BEGIN
			
			IF @vGLConstAccountId IS NOT NULL
				BEGIN
					--foreach gl account sum amount and create InvoiceDetail record. then add update the SO table for the SOID and GUID provided
					SELECT @vInvoiceDetailAmount = SUM(ROUND(CAST(rgp.FixedCost AS decimal(10,2)) * so.Quantity * rgp.GLPercent,2,0))
					  FROM [dbo].[ServiceOrder] so
					  LEFT OUTER JOIN dbo.WorkFlow wf ON wf.ServiceOrderId = so.ServiceOrderId
					  LEFT OUTER JOIN dbo.RateGroup rg ON rg.RateGroupName = wf.WorkGroup
					  LEFT OUTER JOIN dbo.RateGroupPrice rgp ON rgp.AssemblyGuid = so.SoTypeCode AND rgp.RateGroupId = rg.RateGroupId
					  WHERE so.InvoiceSubmitGuid = @IN_GUID
					  AND rgp.GLConstAccountId = @vGLConstAccountId

					SELECT @vGLAccount = GL_Account, @vGLDescription = GL_Description FROM dbo.GLAccount where GLAccountId = @vGLConstAccountId

					--insert into InvoiceDetail table
					INSERT INTO dbo.InvoiceDetail
					(InvoiceId, Description, Amount, GL_Code, GLDepartment, GLActivity)
					VALUES
					(@OUT_InvoiceId, @vGLDescription, @vInvoiceDetailAmount, @vGLAccount, @vGLDepartment, @vGLActivity) 

					SELECT @vInvoiceDetailId = SCOPE_IDENTITY()

					UPDATE dbo.Invoice
					SET InvoiceAmount = (SELECT InvoiceAmount FROM dbo.Invoice WHERE InvoiceId = @OUT_InvoiceId) + @vInvoiceDetailAmount
					WHERE InvoiceId = @OUT_InvoiceId
				END

			IF @vGLRetireAccountId IS NOT NULL
				BEGIN
					--foreach gl account sum amount and create InvoiceDetail record. then add update the SO table for the SOID and GUID provided
					SELECT @vInvoiceDetailAmount = SUM(ROUND(CAST(rgp.FixedCost AS decimal(10,2)) * so.Quantity * rgp.GLPercent,2,1))
					  FROM [dbo].[ServiceOrder] so
					  LEFT OUTER JOIN dbo.WorkFlow wf ON wf.ServiceOrderId = so.ServiceOrderId
					  LEFT OUTER JOIN dbo.RateGroup rg ON rg.RateGroupName = wf.WorkGroup
					  LEFT OUTER JOIN dbo.RateGroupPrice rgp ON rgp.AssemblyGuid = so.SoTypeCode AND rgp.RateGroupId = rg.RateGroupId
					  WHERE so.InvoiceSubmitGuid = @IN_GUID
					  AND rgp.GLRetireAccountId = @vGLRetireAccountId

					SELECT @vGLAccount = GL_Account, @vGLDescription = GL_Description FROM dbo.GLAccount where GLAccountId = @vGLRetireAccountId

					--insert into InvoiceDetail table
					INSERT INTO dbo.InvoiceDetail
					(InvoiceId, Description, Amount, GL_Code, GLDepartment, GLActivity)
					VALUES
					(@OUT_InvoiceId, @vGLDescription, @vInvoiceDetailAmount, @vGLAccount, @vGLRetireDepartment, @vGLRetireActivity) 

					SELECT @vInvoiceDetailId = SCOPE_IDENTITY()

					UPDATE dbo.Invoice
					SET InvoiceAmount = (SELECT InvoiceAmount FROM dbo.Invoice WHERE InvoiceId = @OUT_InvoiceId) + @vInvoiceDetailAmount
					WHERE InvoiceId = @OUT_InvoiceId
				END

			FETCH NEXT FROM MY_CURSOR INTO  @vGLConstAccountId, @vGLRetireAccountId, @vGLDepartment, @vGLActivity, @vGLRetireDepartment, @vGLRetireActivity

		  END
		  CLOSE MY_CURSOR
		  DEALLOCATE MY_CURSOR

			--Update WorkFlow table with Overall invoice status
			UPDATE dbo.WorkFlow
			SET OverallInvoiceStatusId = '2'
			WHERE ServiceOrderId IN (SELECT ServiceOrderId FROM ServiceOrder WHERE InvoiceSubmitGuid = @IN_GUID)

			--update the service order table with the ID for both inserts above
			UPDATE dbo.ServiceOrder
			SET InvoiceId = @OUT_InvoiceId,
				InvoiceDetailId = @vInvoiceDetailId,
				InvoiceStatusId = '2',
				InvoiceSubmitGuid = NULL
			WHERE InvoiceSubmitGuid = @IN_GUID

			--Update the Invoice Record status to 'SUBMITTED'
			UPDATE dbo.Invoice SET InvoiceStatusId = '2' WHERE InvoiceId = @OUT_InvoiceId
			
		END

	ELSE
		BEGIN
		SET @OUT_InvoiceId = '999999'
		END

END

GO
/****** Object:  StoredProcedure [dbo].[UPDATE_WORKFLOW_CALC]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- ==========================================================
-- Author:		Patrick Bowles
-- Create date: June 20, 2018
-- Description:	Update WorkFlow with details from staking
-- ==========================================================
CREATE PROCEDURE [dbo].[UPDATE_WORKFLOW_CALC] 
	-- Add the parameters for the stored procedure here
	@IN_StakingSheetId int,
	@IN_RateDate datetime,
	@IN_TypeFlag varchar(10),
	@OUT_Response int OUTPUT
AS
BEGIN
	
	DECLARE @vDesignCost decimal(10,2),
			@vDesignConstruct decimal(10,2),
			@vDesignRetire decimal(10,2),
			@vDesignStation int,
			@vDesignUniqueAssembly int,
			@vDesignTotalAssembly int,
			
			@vInvoiceCost decimal(10,2),
			@vInvoiceConstruct decimal(10,2),
			@vInvoiceRetire decimal(10,2),
			@vInvoiceUniqueAssembly int,
			@vInvoiceTotalAssembly int,
			@vInvoiceTotalStation int,

			@vInvoiceVoucherCost decimal(10,2),
			@vInvoiceVoucherStations int,
			@vInvoiceVoucherQty int,

			@vWorkFlowId int,
			@vOverallAsBuiltCount int,
			@vOverallInspectionCount int,
			@vOverallInvoiceCount int,
			@vOverallAsBuiltStatusId varchar(38),
			@vOverallInspectionStatusId int,
			@vOverallInvoiceStatusId int,
			@vOverallInspectionVoucherCount int,
			@vOverallInvoiceVoucherCount int

	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	IF @IN_TypeFlag = 'INIT'
		BEGIN
			SELECT @vDesignConstruct = isnull(SUM(ssd.AssemblyQuantity * CAST((rgp.LaborConstCost * rgp.LaborConstHours) AS decimal(10,2))), 0)
			  FROM dbo.StakingSheetDetail ssd 
			  LEFT OUTER JOIN dbo.RateGroupPrice rgp ON rgp.AssemblyGuid = ssd.AssemblyGuid AND rgp.RateGroupId = ssd.AssemblyRateGroupId
			  WHERE StakingSheetId = @IN_StakingSheetId AND AssemblyQuantity > 0 
			  AND @IN_RateDate BETWEEN rgp.EffectiveStartDt AND rgp.EffectiveEndDt
			  AND ssd.AssemblyActionCode = 'C'

			SELECT @vDesignRetire = isnull(SUM(ssd.AssemblyQuantity * CAST((rgp.LaborRetireCost * rgp.LaborRetireHours) AS decimal(10,2))), 0)
						  FROM dbo.StakingSheetDetail ssd 
						  LEFT OUTER JOIN dbo.RateGroupPrice rgp ON rgp.AssemblyGuid = ssd.AssemblyGuid AND rgp.RateGroupId = ssd.AssemblyRateGroupId
						  WHERE StakingSheetId = @IN_StakingSheetId AND AssemblyQuantity > 0 
						  AND @IN_RateDate BETWEEN rgp.EffectiveStartDt AND rgp.EffectiveEndDt
						  AND ssd.AssemblyActionCode = 'R'

			SET @vDesignCost = @vDesignConstruct + @vDesignRetire
			SELECT @vDesignStation = count(Distinct StationDescription) FROM dbo.StakingSheetDetail where StakingSheetId = @IN_StakingSheetId AND AssemblyQuantity > 0
			SELECT @vDesignUniqueAssembly = count(Distinct AssemblyGuid) FROM dbo.StakingSheetDetail where StakingSheetId = @IN_StakingSheetId  AND AssemblyQuantity > 0
			SELECT @vDesignTotalAssembly = count(AssemblyGuid) FROM dbo.StakingSheetDetail where StakingSheetId = @IN_StakingSheetId  AND AssemblyQuantity > 0

			SELECT @vWorkFlowId = WorkFlowId FROM dbo.StakingSheet WHERE StakingSheetId = @IN_StakingSheetId

			UPDATE dbo.WorkFlow
				SET DesignCost = @vDesignCost,
					DesignTotalStation = @vDesignStation,
					DesignUniqueAssembly = @vDesignUniqueAssembly,
					DesignTotalAssembly = @vDesignTotalAssembly,
					InvoiceCost = 0,
					InvoiceUniqueAssembly = 0,
					InvoiceTotalAssembly = 0,
					InvoiceTotalStation = 0,
					InvoiceVoucherCost = 0,
					InvoiceVoucherStation =0,
					InvoiceVoucherQty = 0,
					OverallAsBuiltStatusId = (SELECT AsBuiltStatusId FROM dbo.AsBuiltStatus WHERE Description = 'Not Started'),
					OverallInspectionStatusId = (SELECT InspectionStatusId FROM dbo.InspectionStatus WHERE Status = 'Not Inspected'),
					OverallInvoiceStatusId = (SELECT InvoiceStatusId FROM dbo.InvoiceStatus WHERE Description = 'Not Invoiced')
				WHERE WorkFlowId = @vWorkFlowId

			SET @OUT_Response = 1
		END
	ELSE IF @IN_TypeFlag = 'INV'  --Invoice Submit by Vendor
		BEGIN

		SELECT @vWorkFlowId = WorkFlowId FROM dbo.StakingSheet WHERE StakingSheetId = @IN_StakingSheetId

		SELECT @vInvoiceConstruct = isnull(SUM(ssd.AsBuiltQuantity * CAST(rgp.LaborConstCost * rgp.LaborConstHours AS decimal(10,2))), 0)
			FROM dbo.StakingSheetDetail ssd 
			LEFT OUTER JOIN dbo.RateGroupPrice rgp ON rgp.AssemblyGuid = ssd.AssemblyGuid AND rgp.RateGroupId = ssd.AssemblyRateGroupId
			WHERE StakingSheetId = @IN_StakingSheetId
			AND InvoiceStatusId IN (SELECT InvoiceStatusId FROM dbo.InvoiceStatus WHERE Description = 'Submitted' OR Description = 'Approved')
			AND @IN_RateDate BETWEEN rgp.EffectiveStartDt AND rgp.EffectiveEndDt
			AND ssd.AssemblyActionCode = 'C'

		SELECT @vInvoiceRetire = isnull(SUM(ssd.AsBuiltQuantity * CAST(rgp.LaborRetireCost * rgp.LaborRetireHours AS decimal(10,2))), 0)
			FROM dbo.StakingSheetDetail ssd 
			LEFT OUTER JOIN dbo.RateGroupPrice rgp ON rgp.AssemblyGuid = ssd.AssemblyGuid AND rgp.RateGroupId = ssd.AssemblyRateGroupId
			WHERE StakingSheetId = @IN_StakingSheetId
			AND InvoiceStatusId IN (SELECT InvoiceStatusId FROM dbo.InvoiceStatus WHERE Description = 'Submitted' OR Description = 'Approved')
			AND @IN_RateDate BETWEEN rgp.EffectiveStartDt AND rgp.EffectiveEndDt
			AND ssd.AssemblyActionCode = 'R'

		SELECT 	@vInvoiceTotalStation = count(DISTINCT ssd.StationDescription),
			@vInvoiceUniqueAssembly = count(DISTINCT ssd.AssemblyGuid),
			@vInvoiceTotalAssembly = SUM(ssd.AsBuiltQuantity)
			FROM dbo.StakingSheetDetail ssd 
			LEFT OUTER JOIN dbo.RateGroupPrice rgp ON rgp.AssemblyGuid = ssd.AssemblyGuid AND rgp.RateGroupId = ssd.AssemblyRateGroupId
			WHERE StakingSheetId = @IN_StakingSheetId
			AND InvoiceStatusId IN (SELECT InvoiceStatusId FROM dbo.InvoiceStatus WHERE Description = 'Submitted' OR Description = 'Approved')
			AND @IN_RateDate BETWEEN rgp.EffectiveStartDt AND rgp.EffectiveEndDt

		SET @vInvoiceCost = @vInvoiceConstruct + @vInvoiceRetire

		SELECT @vInvoiceVoucherStations = count(DISTINCT StationDescription), 
			   @vInvoiceVoucherCost = sum(Amount),
			   @vInvoiceVoucherQty = count(*) FROM dbo.Voucher WHERE StakingSheetId = 1
			AND InvoiceStatusId IN (SELECT InvoiceStatusId FROM dbo.InvoiceStatus WHERE Description <> 'Not Invoiced' AND Description <> 'Rejected' AND Description <> 'Partially Invoiced')

		--Retuns count of all the record where AsBuiltStatus not in completed or approved for the given StakingSheetId
		SELECT @vOverallAsBuiltCount = count(*) FROM dbo.StakingSheetDetail ssd 
				WHERE ssd.StakingSheetId = @IN_StakingSheetId
				AND (ssd.AsBuiltStatusId IN (SELECT AsBuiltStatusId FROM dbo.AsBuiltStatus WHERE Description <> 'Completed' AND Description <> 'Approved')
				OR ssd.AsBuiltStatusId IS NULL)

		IF @vOverallAsBuiltCount > 0
			BEGIN
				SELECT @vOverallAsBuiltStatusId = AsBuiltStatusId FROM dbo.AsBuiltStatus WHERE Description = 'In Progress' --OverallAsBuiltStatusId FROM dbo.WorkFlow WHERE WorkFlowId = @vWorkFlowId
			END
		ELSE 
			BEGIN
				SELECT @vOverallAsBuiltStatusId = AsBuiltStatusId FROM dbo.AsBuiltStatus WHERE Description = 'Completed' --Depending on the "Approved" AsBuilt Status
			END


		--Retuns count of all the record where InspectionStatus not in complete for the given StakingSheetId
		SELECT @vOverallInspectionCount = count(*) FROM dbo.StakingSheetDetail ssd 
				WHERE ssd.StakingSheetId = @IN_StakingSheetId
				AND (ssd.CurrentInspectionDetailStatusId IN (SELECT InspectionStatusId FROM dbo.InspectionStatus WHERE Status <> 'Approved')
					OR ssd.CurrentInspectionDetailStatusId IS NULL)

		--Retuns count of all the record where InspectionStatus not in complete for the given StakingSheetId
		SELECT @vOverallInspectionVoucherCount = count(*) FROM dbo.Voucher WHERE StakingSheetId = 1 
			AND InspectionStatusId IN (SELECT InspectionStatusId FROM dbo.InspectionStatus WHERE Status <> 'Approved')

		IF @vOverallInspectionCount > 0 OR @vOverallInspectionVoucherCount > 0
			BEGIN
				SELECT @vOverallInspectionStatusId = InspectionStatusId FROM dbo.InspectionStatus WHERE Status = 'In Progress'
			END
		ELSE 
			BEGIN
				SELECT @vOverallInspectionStatusId = InspectionStatusId FROM dbo.InspectionStatus WHERE Status = 'Approved'
			END
		
		--Retuns count of all the record where InvoiceStatus not in complete for the given StakingSheetId
		SELECT @vOverallInvoiceCount = count(*) FROM dbo.StakingSheetDetail ssd 
				WHERE ssd.StakingSheetId = @IN_StakingSheetId
				AND (ssd.InvoiceStatusId IN (SELECT InvoiceStatusId FROM dbo.InvoiceStatus WHERE Description <> 'Submitted' AND Description <> 'Approved')
					OR ssd.InvoiceStatusId IS NULL)

		--Retuns count of all the record where InvoiceStatus not submitted or approved for the given StakingSheetId
		SELECT @vOverallInvoiceVoucherCount = count(*) FROM dbo.Voucher WHERE StakingSheetId = 1 
			AND InvoiceStatusId IN (SELECT InvoiceStatusId FROM dbo.InvoiceStatus WHERE Description <> 'Submitted' AND Description <> 'Approved')

		IF @vOverallInvoiceCount > 0 OR @vOverallInvoiceVoucherCount > 0
			BEGIN
				SELECT @vOverallInvoiceStatusId = InvoiceStatusId FROM dbo.InvoiceStatus WHERE Description = 'Partially Invoiced'
			END
		ELSE 
			BEGIN
				SELECT @vOverallInvoiceStatusId = InvoiceStatusId FROM dbo.InvoiceStatus WHERE Description = 'Approved'
			END


			UPDATE dbo.WorkFlow
				SET InvoiceCost = @vInvoiceCost,
					InvoiceUniqueAssembly = @vInvoiceUniqueAssembly,
					InvoiceTotalAssembly = @vInvoiceTotalAssembly,
					InvoiceVoucherCost = @vInvoiceVoucherCost,
					InvoiceVoucherStation = @vInvoiceVoucherStations,
					InvoiceVoucherQty = @vInvoiceVoucherQty,
					InvoiceTotalStation = @vInvoiceTotalStation,
					OverallAsBuiltStatusId = @vOverallAsBuiltStatusId,
					OverallInspectionStatusId = @vOverallInspectionStatusId,
					OverallInvoiceStatusId = @vOverallInvoiceStatusId
				WHERE WorkFlowId = @vWorkFlowId

			SET @OUT_Response = 1
		END
	ELSE
		BEGIN
			SET @OUT_Response = 0
		END
END
GO
/****** Object:  StoredProcedure [dbo].[USERTBL_SYNCTO_RESOURCE]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- ==============================================================================
-- Author:		Patrick Bowles
-- Create date: September 22, 2018
-- Description:	Sync process for VPSecurity Users to VendorPortal Resource table
-- ==============================================================================
CREATE PROCEDURE [dbo].[USERTBL_SYNCTO_RESOURCE]
AS

BEGIN

DECLARE @vUID int, @vName varchar(101), @vBeginDate datetime, @vResourceID int = 10005


DECLARE MY_CURSOR CURSOR
LOCAL STATIC FORWARD_ONLY
FOR
SELECT UserID, FirstName + ' ' + LastName AS Name FROM sec.UserTbl

OPEN MY_CURSOR
FETCH NEXT FROM MY_CURSOR INTO  @vUID, @vName
WHILE @@FETCH_STATUS = 0
	BEGIN
		SELECT @vBeginDate = EffectiveStartDate FROM sec.UserTbl WHERE UserID = @vUID
		IF ((SELECT count(*) FROM dbo.Resource WHERE ResourceName = @vName) = 1)
			BEGIN
				UPDATE dbo.Resource SET VpUserId = @vUID 
				WHERE ResourceId = (SELECT ResourceId FROM dbo.Resource WHERE ResourceName = @vName) 
			END
		ELSE
			BEGIN
				INSERT INTO dbo.Resource
				(ResourceId, ResourceName, VpUserID, EmployeeNumber, Active, EffectiveBeginDt, SourceSystem)
				VALUES
				(@vResourceID, @vName, @vUID, 0, 'Y', @vBeginDate, 'VP')

				SET @vResourceID = @vResourceID + 1
			END
		FETCH NEXT FROM MY_CURSOR INTO  @vUID, @vName
		
	END

	CLOSE MY_CURSOR
	DEALLOCATE MY_CURSOR

END
GO
/****** Object:  StoredProcedure [sec].[DeleteUser]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Patrick Bowles
-- Create date: September 28, 2018
-- Description:	Hard Delete user accounts from VendorPortal
-- =============================================
CREATE PROCEDURE [sec].[DeleteUser]
	@IN_Username varchar(50)
AS
BEGIN
	
	DECLARE @vUserID int

	SET NOCOUNT ON;

	SELECT @vUserID = UserID FROM sec.UserTbl WHERE UserName = @IN_Username

	DELETE FROM sec.UserRole WHERE UserID = @vUserID
	DELETE FROM sec.LoginAttempt WHERE UserID = @vUserID
	DELETE FROM sec.UserTbl WHERE UserID = @vUserID
END
GO
/****** Object:  StoredProcedure [sec].[DisableUser]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =========================================================
-- Author:		Patrick Bowles
-- Create date: September 28, 2018
-- Description:	Soft Delete user accounts from VendorPortal
-- =========================================================
CREATE PROCEDURE [sec].[DisableUser]
	@IN_Username varchar(50)
AS
BEGIN
	
	DECLARE @vUserID int

	SET NOCOUNT ON;

	SELECT @vUserID = UserID FROM sec.UserTbl WHERE UserName = @IN_Username

	DELETE FROM sec.UserRole WHERE UserID = @vUserID
	
	UPDATE sec.UserTbl SET EffectiveEndDate = GETDATE(), IsLocked = 1 WHERE UserID = @vUserID
END
GO
/****** Object:  StoredProcedure [sec].[NewRolePermission]    Script Date: 1/7/2021 10:53:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Patrick Bowles
-- Create date: September 22, 2018
-- Description:	Insert New Permission for Role
-- =============================================
CREATE PROCEDURE [sec].[NewRolePermission]
	@IN_RoleName varchar(128),
	@IN_Domain varchar(50),
	@IN_Permission varchar(128),
	@Result varchar(50) OUTPUT
AS
BEGIN
	
	SET NOCOUNT ON;

    DECLARE @vPermissionID int, @vRoleID int, @vExistsCount int

	SELECT @vPermissionID = PermissionID FROM sec.Permission WHERE Domain = @IN_Domain AND Permission = @IN_Permission
	
	IF (@vPermissionID IS NOT NULL)
		BEGIN
			
			SELECT @vRoleID = RoleID FROM sec.Role WHERE RoleName = @IN_RoleName
			IF (@vRoleID IS NOT NULL)
				BEGIN
								
					SELECT @vExistsCount = count(*) FROM sec.RolePermission WHERE PermissionID = @vPermissionID AND RoleID = @vRoleID

					IF (@vExistsCount = 0)
						BEGIN

							INSERT INTO sec.RolePermission
							(RoleID, PermissionID, EffectiveStartDate)
							VALUES
							(@vRoleID, @vPermissionID, getdate())

							SET @Result = 'Insert Successful'

						END
					ELSE
						BEGIN

							SET @Result = 'Already Exists'

						END
				END
			ELSE
				BEGIN
					SET @Result = 'Invalid Role'
				END
		END
	ELSE
		BEGIN
			SET @Result = 'Invalid Permission'
		END
END
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "StakingSheetDetail (dbo)"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 321
               Right = 477
            End
            DisplayFlags = 280
            TopColumn = 10
         End
         Begin Table = "InvoiceStatus (dbo)"
            Begin Extent = 
               Top = 74
               Left = 816
               Bottom = 186
               Right = 1002
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 12
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'AsBuiltSummaryVw'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'AsBuiltSummaryVw'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "Invoice (dbo)"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 235
               Right = 303
            End
            DisplayFlags = 280
            TopColumn = 5
         End
         Begin Table = "WorkFlow (dbo)"
            Begin Extent = 
               Top = 25
               Left = 494
               Bottom = 282
               Right = 781
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 17
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'InvoiceSearchVw'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'InvoiceSearchVw'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "RateGroup (dbo)"
            Begin Extent = 
               Top = 11
               Left = 49
               Bottom = 266
               Right = 246
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "RateGroupPrice (dbo)"
            Begin Extent = 
               Top = 11
               Left = 528
               Bottom = 276
               Right = 741
            End
            DisplayFlags = 280
            TopColumn = 6
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 12
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'RateGroupVw'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'RateGroupVw'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "so"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 135
               Right = 254
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "rgp"
            Begin Extent = 
               Top = 138
               Left = 38
               Bottom = 267
               Right = 238
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "wf"
            Begin Extent = 
               Top = 270
               Left = 38
               Bottom = 399
               Right = 261
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "rg"
            Begin Extent = 
               Top = 6
               Left = 292
               Bottom = 135
               Right = 469
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "gl"
            Begin Extent = 
               Top = 138
               Left = 276
               Bottom = 250
               Right = 446
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         O' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'StreetLightSearchVw'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane2', @value=N'utput = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'StreetLightSearchVw'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'StreetLightSearchVw'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "u"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 135
               Right = 220
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "ur"
            Begin Extent = 
               Top = 6
               Left = 258
               Bottom = 118
               Right = 428
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "rp"
            Begin Extent = 
               Top = 120
               Left = 258
               Bottom = 249
               Right = 440
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "p"
            Begin Extent = 
               Top = 138
               Left = 38
               Bottom = 267
               Right = 220
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'sec', @level1type=N'VIEW',@level1name=N'PermissionVW'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'sec', @level1type=N'VIEW',@level1name=N'PermissionVW'
GO
